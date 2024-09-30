package test;

import Commons.ModuleGenerator;
import Commons.RequestCapability;
import ModuleList.LoginModule;
import ModuleList.PayerModule;
import PGPHandler.AuthUtils;
import PGPHandler.KeyContainer;
import RequestBodyModal.*;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
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
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreatePayerScenario {

    private LoginModule loginModule = ModuleGenerator.getLoginModule();
    private PayerModule payerModule = ModuleGenerator.getPayerModule();
    private LoginReq loginJSONBody;
    private String loginJSONBodyEncryptedStr;
    private Response response;
    private LoginReqEncrypt loginReqEncrypt;
    private String finalBody;
    private String hmacSignature;
    private String accessToken;
    private String refreshIdx;
    private Faker faker = new Faker(new Locale("EN-en"));
    private String payer1Phone;
    private String payer1Name;
    private String payer1Number;

    @Test
    public void TC_01_EncryptLoginBody() throws Exception {
        loginJSONBody = loginModule.getLoginJSONBody();
        System.out.println(new Gson().toJson(loginJSONBody));
        loginJSONBodyEncryptedStr = loginModule.getEncryptData(loginJSONBody, KeyContainer.PUBLIC_KEY);
        loginReqEncrypt = new LoginReqEncrypt(loginJSONBodyEncryptedStr);
        finalBody = new Gson().toJson(loginReqEncrypt);
        System.out.println(finalBody);
    }

    @Test
    public void TC_02_GetAccessToken() throws Exception {
        RequestSpecification request = given();
        hmacSignature = AuthUtils.getAuthHeader();
        request = loginModule.getReqHd(request);
        request.header(new Header("Authorization", hmacSignature));
        response = loginModule.getAccessToken(request, finalBody);
        response.prettyPrint();
        Map<String, String> loginRespFirstLevelKey = JsonPath.from(response.asString()).get();
        String encryptedLoginRespBody = loginRespFirstLevelKey.get("data");
        System.out.println(encryptedLoginRespBody);
        LoginRespDecrypt loginRespDecrypt = loginModule.getBodyDecrypt(KeyContainer.PRIVATE_KEY, KeyContainer.PASS_CODE, encryptedLoginRespBody, LoginRespDecrypt.class);
        String decryptLoginRespBody = new Gson().toJson(loginRespDecrypt);
        System.out.println(decryptLoginRespBody);
        System.out.println(loginRespDecrypt.accessToken);
        System.out.println(loginRespDecrypt.refreshIdx);
        accessToken = loginRespDecrypt.accessToken;
        refreshIdx = loginRespDecrypt.refreshIdx;
    }

    @Test
    public void TC_03_CreatePayer() throws PGPException, IOException {
        CreatePayerReq createPayerReq = new CreatePayerReq();
        CreatePayerReq.Payer payer1 = new CreatePayerReq.Payer("", String.valueOf(faker.team().name()), "03" + faker.number().randomNumber(8, true), "Y", "Y", "", "vk remark");
        CreatePayerReq.Payer payer2 = new CreatePayerReq.Payer("", String.valueOf(faker.team().name()), "03" + faker.number().randomNumber(8, true), "Y", "Y", "", "vk remark");
        createPayerReq.data.add(payer1);
        createPayerReq.data.add(payer2);
        System.out.println(new Gson().toJson(createPayerReq));
        String encryptedData = payerModule.getEncryptData(createPayerReq, KeyContainer.PUBLIC_KEY);
        CommonModal commonModal = new CommonModal(encryptedData);
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
        payer1Name = payer1.getPayerName();
    }

    @Test
    public void TC_04_VerifyDatabase() throws SQLException {
        Connection connection = MariaDBConnUtils.getMariaDBConnection();
        String queryingString = "SELECT * FROM TB_PAYER WHERE TEL_NO = ?";
        PreparedStatement pstm = connection.prepareStatement(queryingString);
        pstm.setString(1, payer1Phone);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            System.out.println("--------------------");
            System.out.println("Expected payer name: " + payer1Name);
            System.out.println("Actual payer name from DB: " + resultSet.getString("PAYER_NM"));
            Assert.assertEquals(payer1Name, resultSet.getString("PAYER_NM"));
            payer1Number = resultSet.getString("PAYER_NO");
        }

    }

    @Test
    public void TC_05_CreateEccThenMapToPayer() throws PGPException, IOException {
        RequestSpecification request = given();
        request.baseUri(RequestCapability.BASE_URL);
        request.basePath("/ocms/v2/ec/create_eccode");
        request.header("token", accessToken);
        request.header("Content-Type", "application/json; charset=UTF-8");
        request.header("AuthorizationHeaderParameters", "MTdlOGZmZWU2YTI2MTA0MmY2ZWIyNmY1MWNlODlkMTA=");
        request.header("bankCode", "01202001");

        CreateEccMapPayerReq createEccMapPayerReq = new CreateEccMapPayerReq();
        CreateEccMapPayerReq.PayerEccPair pair1 = new CreateEccMapPayerReq.PayerEccPair(payer1Number, "7834444422344", "vk holder name");
        createEccMapPayerReq.data.add(pair1);

        String encryptedData = payerModule.getEncryptData(createEccMapPayerReq, KeyContainer.PUBLIC_KEY);
        CommonModal commonModal = new CommonModal(encryptedData);

        Response response1 = request.body(commonModal).post();
        response1.prettyPrint();


    }

    @Test
    public void TC_06_CreateReceivable() {
        RequestSpecification request = given();
        request.baseUri(RequestCapability.BASE_URL);
        request.basePath("/ocms/v2/ec/create_eccode_recv");
        request.header("token", accessToken);
        request.header("Content-Type", "application/json; charset=UTF-8");
        request.header("AuthorizationHeaderParameters", "MTdlOGZmZWU2YTI2MTA0MmY2ZWIyNmY1MWNlODlkMTA=");
        request.header("bankCode", "01202001");

        CreateReceivableReq createReceivableReq = new CreateReceivableReq();
        CreateReceivableReq.Receivable receivable1 = new CreateReceivableReq.Receivable("7834444422344", payer1Number, )

        CreateEccMapPayerReq createEccMapPayerReq = new CreateEccMapPayerReq();
        CreateEccMapPayerReq.PayerEccPair pair1 = new CreateEccMapPayerReq.PayerEccPair(payer1Number, "7834444422344", "vk holder name");
        createEccMapPayerReq.data.add(pair1);

        String encryptedData = payerModule.getEncryptData(createEccMapPayerReq, KeyContainer.PUBLIC_KEY);
        CommonModal commonModal = new CommonModal(encryptedData);

        Response response1 = request.body(commonModal).post();
        response1.prettyPrint();
    }

}
