package test;

import BasePath.BasePathList;
import Commons.*;
import PGPHandler.KeyContainer;
import RequestBodyModal.CreateEcReceivableRequestBody;
import RequestBodyModal.CreateEccRequestBody;
import RequestBodyModal.CreatePayerRequestBody;
import RequestBodyModal.LoginRequestBody;
import ResponseBodyModal.CreateEccResponseBody;
import ResponseBodyModal.CreatePayerResponseBody;
import ResponseBodyModal.LoginResponseBody;
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
    private String ecc;

    @Test
    public void TC_01_EncryptLoginBody() throws Exception {
        LoginRequestBody loginRequestBody = RequestBodyGenerator.getLoginRequestBody();
        String encryptedLoginBody = BaseTest.getEncryptData(loginRequestBody, KeyContainer.PUBLIC_KEY);
        finalRequestBody = RequestBodyGenerator.getFinalRequestBody(encryptedLoginBody);

        /*loginReqBody = RequestBodyGenerator.getLoginReqBody();
        System.out.println(new Gson().toJson(loginReqBody));
        String encryptedLoginBody = BaseTest.getEncryptData(loginReqBody, KeyContainer.PUBLIC_KEY);
        loginReqEncrypt = new LoginReqEncrypt(loginJSONBodyEncryptedStr);
        finalRequestBody = new Gson().toJson(loginReqEncrypt);
        System.out.println(finalRequestBody);*/

    }

    @Test
    public void TC_02_LogIn() throws Exception {
        request = RequestHeaderGenerator.getLoginRequestHeader();
        response = request.body(finalRequestBody).post();
        originalResponseBody = response.as(OriginalResponseBody.class);
        originalDataValue = originalResponseBody.data;
        LoginResponseBody loginResponseBody = BaseTest.getBodyDecrypt(KeyContainer.PRIVATE_KEY, KeyContainer.PASS_CODE, originalDataValue, LoginResponseBody.class);
        accessToken = loginResponseBody.accessToken;
        refreshIdx = loginResponseBody.refreshIdx;

        /*RequestSpecification request = given();
        hmacSignature = AuthUtils.getAuthHeader();
        request = loginModule.getReqHd(request);
        request.header(new Header("Authorization", hmacSignature));
        response = loginModule.getAccessToken(request, finalRequestBody);
        response.prettyPrint();
        Map<String, String> loginRespFirstLevelKey = JsonPath.from(response.asString()).get();
        String encryptedLoginRespBody = loginRespFirstLevelKey.get("data");
        System.out.println(encryptedLoginRespBody);
        LoginRespDecrypt loginRespDecrypt = BaseTest.getBodyDecrypt(KeyContainer.PRIVATE_KEY, KeyContainer.PASS_CODE, encryptedLoginRespBody, LoginRespDecrypt.class);
        String decryptLoginRespBody = new Gson().toJson(loginRespDecrypt);
        System.out.println(decryptLoginRespBody);
        System.out.println(loginRespDecrypt.accessToken);
        System.out.println(loginRespDecrypt.refreshIdx);
        accessToken = loginRespDecrypt.accessToken;
        refreshIdx = loginRespDecrypt.refreshIdx;*/
    }

    @Test
    public void TC_03_CreatePayer() throws PGPException, IOException {
        CreatePayerRequestBody createPayerRequestBody = RequestBodyGenerator.getCreatePayerRequestBody();
        String encryptedCreatePayerBody = BaseTest.getEncryptData(createPayerRequestBody, KeyContainer.PUBLIC_KEY);
        finalRequestBody = RequestBodyGenerator.getFinalRequestBody(encryptedCreatePayerBody);
        request = RequestHeaderGenerator.getCustomRequestHeader(BasePathList.CREATE_PAYER, accessToken);
        response = request.body(finalRequestBody).post();
        originalResponseBody = response.as(OriginalResponseBody.class);
        originalDataValue = originalResponseBody.data;
        CreatePayerResponseBody createPayerResponseBody = BaseTest.getBodyDecrypt(KeyContainer.PRIVATE_KEY, KeyContainer.PASS_CODE, originalDataValue, CreatePayerResponseBody.class);
        payerName = createPayerResponseBody.succList.get(0).payerName;
        payerPhoneNo = createPayerResponseBody.succList.get(0).phoneNo;

        /*CreatePayerReq createPayerReq = new CreatePayerReq();
        CreatePayerReq.Payer payer1 = new CreatePayerReq.Payer("", String.valueOf(faker.team().name()), "03" + faker.number().randomNumber(8, true), "Y", "Y", "", "vk remark");
        CreatePayerReq.Payer payer2 = new CreatePayerReq.Payer("", String.valueOf(faker.team().name()), "03" + faker.number().randomNumber(8, true), "Y", "Y", "", "vk remark");
        createPayerReq.data.add(payer1);
        createPayerReq.data.add(payer2);
        System.out.println(new Gson().toJson(createPayerReq));
        String encryptedData = BaseTest.getEncryptData(createPayerReq, KeyContainer.PUBLIC_KEY);
        FinalRequestBody commonModal = new FinalRequestBody(encryptedData);
        RequestSpecification request = given();
        request.baseUri(RequestCapability.BASE_URL);
        request.basePath("/ocms/v1/payer/create_payer");
        request.header("token", accessToken);
        request.header("Content-Type", "application/json; charset=UTF-8");
        request.header("AuthorizationHeaderParameters", "MTdlOGZmZWU2YTI2MTA0MmY2ZWIyNmY1MWNlODlkMTA=");
        request.header("bankCode", "01202001");
        Response response1 = request.body(commonModal).post();
        response1.prettyPrint();
        payer1Phone = payer1.getPhoneNo();
        payer1Name = payer1.getPayerName();*/
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
        String encryptedCreateEccBody = BaseTest.getEncryptData(createEccRequestBody, KeyContainer.PUBLIC_KEY);
        finalRequestBody = RequestBodyGenerator.getFinalRequestBody(encryptedCreateEccBody);
        response = request.body(finalRequestBody).post();
        originalResponseBody = response.as(OriginalResponseBody.class);
        originalDataValue = originalResponseBody.data;
        CreateEccResponseBody createEccResponseBody = BaseTest.getBodyDecrypt(KeyContainer.PRIVATE_KEY, KeyContainer.PASS_CODE, originalDataValue, CreateEccResponseBody.class);
        ecc = createEccResponseBody.succList.get(0).ecollectionCd;

        /*RequestSpecification request = given();
        request.baseUri(RequestCapability.BASE_URL);
        request.basePath("/ocms/v2/ec/create_eccode");
        request.header("token", accessToken);
        request.header("Content-Type", "application/json; charset=UTF-8");
        request.header("AuthorizationHeaderParameters", "MTdlOGZmZWU2YTI2MTA0MmY2ZWIyNmY1MWNlODlkMTA=");
        request.header("bankCode", "01202001");

        CreateEccMapPayerReq createEccMapPayerReq = new CreateEccMapPayerReq();
        CreateEccMapPayerReq.PayerEccPair pair1 = new CreateEccMapPayerReq.PayerEccPair(payerNo, "7834444422344", "vk holder name");
        createEccMapPayerReq.data.add(pair1);

        String encryptedData = BaseTest.getEncryptData(createEccMapPayerReq, KeyContainer.PUBLIC_KEY);
        FinalRequestBody commonModal = new FinalRequestBody(encryptedData);

        Response response1 = request.body(commonModal).post();
        response1.prettyPrint();

        Map<String, String> createPayerRespFirstLevelKey = JsonPath.from(response1.asString()).get();
        String encryptedLoginRespBody = createPayerRespFirstLevelKey.get("data");

        CreateEccMapPayerResp createEccMapPayerResp = BaseTest.getBodyDecrypt(KeyContainer.PRIVATE_KEY, KeyContainer.PASS_CODE, encryptedLoginRespBody, CreateEccMapPayerResp.class);
        String decryptCreatePayerRespBody = new Gson().toJson(createEccMapPayerResp);
        System.out.println(decryptCreatePayerRespBody);
        ecc = createEccMapPayerResp.succList.get(0).ecollectionCd;

        System.out.println(ecc);*/
    }

    @Test
    public void TC_06_CreateReceivable() throws PGPException, IOException {
        request = RequestHeaderGenerator.getCustomRequestHeader(BasePathList.CREATE_RECEIVABLE, accessToken);
        CreateEcReceivableRequestBody createEcReceivableRequest = RequestBodyGenerator.getCreateEcReceivableRequest(payerNo, ecc);
        String encryptedCreateEcReceivableBody = BaseTest.getEncryptData(createEcReceivableRequest, KeyContainer.PUBLIC_KEY);
        finalRequestBody = RequestBodyGenerator.getFinalRequestBody(encryptedCreateEcReceivableBody);
        response = request.body(finalRequestBody).post();

        /*RequestSpecification request = given();
        request.baseUri(RequestCapability.BASE_URL);
        request.basePath("/ocms/v2/ec/create_eccode_recv");
        request.header("token", accessToken);
        request.header("Content-Type", "application/json; charset=UTF-8");
        request.header("AuthorizationHeaderParameters", "MTdlOGZmZWU2YTI2MTA0MmY2ZWIyNmY1MWNlODlkMTA=");
        request.header("bankCode", "01202001");

        CreateReceivableReq createReceivableReq = new CreateReceivableReq();
        CreateReceivableReq.Receivable receivable1 = new CreateReceivableReq.Receivable("7834444422344", payerNo, ecc, "","0",200000, "20241205", "20241207", "", "", "0");
        createReceivableReq.data.add(receivable1);

        String encryptedData = BaseTest.getEncryptData(createReceivableReq, KeyContainer.PUBLIC_KEY);
        FinalRequestBody commonModal = new FinalRequestBody(encryptedData);

        Response response1 = request.body(commonModal).post();
        response1.prettyPrint();*/
    }

}
