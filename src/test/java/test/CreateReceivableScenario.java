package test;

import BasePath.PathList;
import Commons.*;
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
    String originalDataValue;

    @Test
    public void TC_01_LogInToOpenAPI() throws Exception {
        LoginRequestBody loginRequestBody = RequestBodyGenerator.getLoginRequestBody();
        String encryptedBody = BaseTest.getEncryptData(loginRequestBody);
        finalRequestBody = RequestBodyGenerator.getFinalRequestBody(encryptedBody);
        request = RequestHeaderGenerator.getLoginRequestHeader();
        response = request.body(finalRequestBody).post();
        originalDataValue = response.as(OriginalResponseBody.class).data;
        LoginResponseBody loginResponseBody = BaseTest.getBodyDecrypt(originalDataValue, LoginResponseBody.class);
        RequestCapability.openAPIAccessToken = loginResponseBody.accessToken;
        RequestCapability.openAPIRefreshIdx = loginResponseBody.refreshIdx;
    }

    @Test
    public void TC_02_CreatePayer() throws PGPException, IOException {
        CreatePayerRequestBody createPayerRequestBody = RequestBodyGenerator.getCreatePayerRequestBody();

        System.out.println("Create Payer Request: " + new Gson().toJson(createPayerRequestBody));

        String encryptedBody = BaseTest.getEncryptData(createPayerRequestBody);
        finalRequestBody = RequestBodyGenerator.getFinalRequestBody(encryptedBody);
        request = RequestHeaderGenerator.getOpenAPICustomRequestHeader(PathList.CREATE_PAYER);
        response = request.body(finalRequestBody).post();
        originalDataValue = response.as(OriginalResponseBody.class).data;
        CreatePayerResponseBody createPayerResponseBody = BaseTest.getBodyDecrypt(originalDataValue, CreatePayerResponseBody.class);

        System.out.println("Create Payer Response: " + new Gson().toJson(createPayerResponseBody));

        String actualPayerName = createPayerResponseBody.succList.get(0).payerName;
        String actualPhoneNo = createPayerResponseBody.succList.get(0).phoneNo;

        Assert.assertEquals(SampleData.payerName, actualPayerName);
        Assert.assertEquals(SampleData.phoneNo, actualPhoneNo);
    }

    @Test
    public void TC_03_VerifyDatabase() throws SQLException {
        Connection connection = MariaDBConnUtils.getMariaDBConnection();
        String queryingString = "SELECT * FROM TB_PAYER WHERE TEL_NO = ?";
        PreparedStatement pstm = connection.prepareStatement(queryingString);
        pstm.setString(1, SampleData.phoneNo);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            Assert.assertEquals(SampleData.payerName, resultSet.getString("PAYER_NM"));
            SampleData.payerNo = resultSet.getString("PAYER_NO");
        }
    }

    @Test
    public void TC_04_CreateEccThenMapToPayer() throws PGPException, IOException {
        request = RequestHeaderGenerator.getOpenAPICustomRequestHeader(PathList.CREATE_ECC);
        CreateEccRequestBody createEccRequestBody = RequestBodyGenerator.getCreateEccRequestBody();

        System.out.println("Create Ecc Request: " + new Gson().toJson(createEccRequestBody));

        String encryptedBody = BaseTest.getEncryptData(createEccRequestBody);
        finalRequestBody = RequestBodyGenerator.getFinalRequestBody(encryptedBody);
        response = request.body(finalRequestBody).post();
        originalDataValue = response.as(OriginalResponseBody.class).data;
        CreateEccResponseBody createEccResponseBody = BaseTest.getBodyDecrypt(originalDataValue, CreateEccResponseBody.class);

        System.out.println(new Gson().toJson(createEccResponseBody));

        SampleData.originalEcc = createEccResponseBody.succList.get(0).ecollectionCd;
        SampleData.cuttedPrefixEcc = SampleData.originalEcc.replace("HHHHH", "");

        System.out.println("Create Ecc Response: " + new Gson().toJson(createEccResponseBody));

        String actualPayerNo = createEccResponseBody.succList.get(0).payerNo;
        Assert.assertEquals(SampleData.payerNo, actualPayerNo);
    }

    @Test
    public void TC_05_CreateReceivable() throws PGPException, IOException {
        request = RequestHeaderGenerator.getOpenAPICustomRequestHeader(PathList.CREATE_RECEIVABLE);
        CreateEcReceivableRequestBody createEcReceivableRequest = RequestBodyGenerator.getCreateEcReceivableRequest();

        System.out.println("Create Ec Receivable Request: " + new Gson().toJson(createEcReceivableRequest));

        String encryptedBody = BaseTest.getEncryptData(createEcReceivableRequest);
        finalRequestBody = RequestBodyGenerator.getFinalRequestBody(encryptedBody);
        response = request.body(finalRequestBody).post();
        originalDataValue = response.as(OriginalResponseBody.class).data;
        CreateEcReceivableResponsebody createEcReceivableResponsebody = BaseTest.getBodyDecrypt(originalDataValue, CreateEcReceivableResponsebody.class);

        System.out.println("Create Ec Receivable Response: " + new Gson().toJson(createEcReceivableResponsebody));

        String actualEcc = createEcReceivableResponsebody.succList.get(0).ecollectionCd;
        Assert.assertEquals(SampleData.originalEcc, actualEcc);
    }

}
