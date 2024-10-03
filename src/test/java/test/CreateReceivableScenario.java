package test;

import BasePath.BasePathList;
import Commons.*;
import PGPHandler.KeyContainer;
import RequestBodyModal.CreateEcReceivableRequestBody;
import RequestBodyModal.CreateEccRequestBody;
import RequestBodyModal.CreatePayerRequestBody;
import RequestBodyModal.LoginRequestBody;
import ResponseBodyModal.CreateEcReceivableResponsebody;
import ResponseBodyModal.CreateEccResponseBody;
import ResponseBodyModal.CreatePayerResponseBody;
import ResponseBodyModal.LoginResponseBody;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdbcTest.MariaDBConnUtils;
import org.bouncycastle.openpgp.PGPException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateReceivableScenario extends BaseTest {

    private FinalRequestBody finalRequestBody;
    private RequestSpecification request;
    private Response response;
    private String accessToken;
    private int refreshIdx;
    OriginalResponseBody originalResponseBody;
    String originalDataValue;
    private String payerPhoneNo;
    private String payerName;
    private String payerNo;

    @Test
    public void TC_01_EncryptLoginBody() throws Exception {
        LoginRequestBody loginRequestBody = RequestBodyGenerator.getLoginRequestBody();
        finalRequestBody = BaseTest.getEncryptData(loginRequestBody);
    }

    @Test
    public void TC_02_LogIn() throws Exception {
        request = RequestHeaderGenerator.getLoginRequestHeader();
        response = request.body(finalRequestBody).post();
        originalDataValue = response.as(OriginalResponseBody.class).data;
        LoginResponseBody loginResponseBody = BaseTest.getBodyDecrypt(KeyContainer.PRIVATE_KEY, KeyContainer.PASS_CODE, originalDataValue, LoginResponseBody.class);
        accessToken = loginResponseBody.accessToken;
        refreshIdx = loginResponseBody.refreshIdx;
    }

    @Test
    public void TC_03_CreatePayer() throws PGPException, IOException {
        CreatePayerRequestBody createPayerRequestBody = RequestBodyGenerator.getCreatePayerRequestBody();

        System.out.println("Create Payer Request: " + new Gson().toJson(createPayerRequestBody));

        finalRequestBody = BaseTest.getEncryptData(createPayerRequestBody);
        request = RequestHeaderGenerator.getCustomRequestHeader(BasePathList.CREATE_PAYER, accessToken);
        response = request.body(finalRequestBody).post();
        originalResponseBody = response.as(OriginalResponseBody.class);
        originalDataValue = originalResponseBody.data;
        CreatePayerResponseBody createPayerResponseBody = BaseTest.getBodyDecrypt(KeyContainer.PRIVATE_KEY, KeyContainer.PASS_CODE, originalDataValue, CreatePayerResponseBody.class);
        payerName = createPayerResponseBody.succList.get(0).payerName;
        payerPhoneNo = createPayerResponseBody.succList.get(0).phoneNo;

        System.out.println("Create Payer Response: " + new Gson().toJson(createPayerResponseBody));

        String expectedPayerName = createPayerRequestBody.data.get(0).payerName;
        String expectedPhoneNo = createPayerRequestBody.data.get(0).phoneNo;

        String actualPayerName = createPayerResponseBody.succList.get(0).payerName;
        String actualPhoneNo = createPayerResponseBody.succList.get(0).phoneNo;

        Assert.assertEquals(expectedPayerName, actualPayerName);
        Assert.assertEquals(expectedPhoneNo, actualPhoneNo);
    }

    @Test
    public void TC_04_VerifyDatabase() throws SQLException {
        Connection connection = MariaDBConnUtils.getMariaDBConnection();
        String queryingString = "SELECT * FROM TB_PAYER WHERE TEL_NO = ?";
        PreparedStatement pstm = connection.prepareStatement(queryingString);
        pstm.setString(1, payerPhoneNo);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            Assert.assertEquals(payerName, resultSet.getString("PAYER_NM"));
            payerNo = resultSet.getString("PAYER_NO");
        }
    }

    @Test
    public void TC_05_CreateEccThenMapToPayer() throws PGPException, IOException {
        request = RequestHeaderGenerator.getCustomRequestHeader(BasePathList.CREATE_ECC, accessToken);
        CreateEccRequestBody createEccRequestBody = RequestBodyGenerator.getCreateEccRequestBody(payerNo);

        System.out.println("Create Ecc Request: " + new Gson().toJson(createEccRequestBody));

        finalRequestBody = BaseTest.getEncryptData(createEccRequestBody);
        response = request.body(finalRequestBody).post();
        originalResponseBody = response.as(OriginalResponseBody.class);
        originalDataValue = originalResponseBody.data;
        CreateEccResponseBody createEccResponseBody = BaseTest.getBodyDecrypt(KeyContainer.PRIVATE_KEY, KeyContainer.PASS_CODE, originalDataValue, CreateEccResponseBody.class);
        RequestCapability.originalEcc = createEccResponseBody.succList.get(0).ecollectionCd;
        RequestCapability.cuttedPrefixEcc = originalEcc.replace("HHHHH", "");

        System.out.println("Create Ecc Response: " + new Gson().toJson(createEccResponseBody));

        String expectedPayerNo = createEccRequestBody.data.get(0).payerNo;
        String actualPayerNo = createEccResponseBody.succList.get(0).payerNo;

        Assert.assertEquals(expectedPayerNo, actualPayerNo);
    }

    @Test
    public void TC_06_CreateReceivable() throws PGPException, IOException {
        request = RequestHeaderGenerator.getCustomRequestHeader(BasePathList.CREATE_RECEIVABLE, accessToken);
        CreateEcReceivableRequestBody createEcReceivableRequest = RequestBodyGenerator.getCreateEcReceivableRequest(payerNo, RequestCapability.originalEcc);

        System.out.println("Create Ec Receivable Request: " + new Gson().toJson(createEcReceivableRequest));

        finalRequestBody = BaseTest.getEncryptData(createEcReceivableRequest);
        response = request.body(finalRequestBody).post();
        originalResponseBody = response.as(OriginalResponseBody.class);
        originalDataValue = originalResponseBody.data;
        CreateEcReceivableResponsebody createEcReceivableResponsebody = BaseTest.getBodyDecrypt(KeyContainer.PRIVATE_KEY, KeyContainer.PASS_CODE, originalDataValue, CreateEcReceivableResponsebody.class);

        System.out.println("Create Ec Receivable Response: " + new Gson().toJson(createEcReceivableResponsebody));

        String expectedEcc = createEcReceivableRequest.data.get(0).ecollectionCd;
        String actualEcc = createEcReceivableResponsebody.succList.get(0).ecollectionCd;

        Assert.assertEquals(expectedEcc, actualEcc);
    }

}
