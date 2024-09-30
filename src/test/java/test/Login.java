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
import org.bouncycastle.openpgp.PGPException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Login {

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

    @Test
    public void TC_01_EncryptLoginBody() throws Exception {

        System.out.println("==========TC_01_EncryptLoginBody");



        /*JSONObject parent = new JSONObject();
        JSONObject child = new JSONObject();
        child.put("masterId", "huytest");
        child.put("clientId", "17e8ffee6a261042f6eb26f51ce89d10");
        parent.put("data", child);
        System.out.println("loginBody: " + parent);

        String jsonString = parent.toString();
        Gson gson = new Gson();
        CreatePayer createPayer = gson.fromJson(jsonString, CreatePayer.class);

        loginJSONBodyEncryptedStr = loginModule.getEncryptData(createPayer, KeyContainer.PUBLIC_KEY);*/


        loginJSONBody = loginModule.getLoginJSONBody();
        System.out.println(new Gson().toJson(loginJSONBody));
        loginJSONBodyEncryptedStr = loginModule.getEncryptData(loginJSONBody, KeyContainer.PUBLIC_KEY);
        loginReqEncrypt = new LoginReqEncrypt(loginJSONBodyEncryptedStr);
        finalBody = new Gson().toJson(loginReqEncrypt);
        System.out.println(finalBody);
    }

    @Test
    public void TC_02_GetAccessToken() throws Exception {

        System.out.println("==========TC_02_GetAccessToken");

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

        /*System.out.println("==========TC_03_CreatePayer");

        JSONObject createPayerReqBody = new JSONObject();
        JSONArray createPayerReqBodyData = new JSONArray();
        JSONObject createPayerReqBodyInfo1 = new JSONObject();
        JSONObject createPayerReqBodyInfo2 = new JSONObject();

        createPayerReqBodyInfo1.put("payerNo", "");
        createPayerReqBodyInfo1.put("payerName", String.valueOf(faker.team().name()));
        createPayerReqBodyInfo1.put("phoneNo", "03" + faker.number().randomNumber(8, true));
        createPayerReqBodyInfo1.put("email", "");
        createPayerReqBodyInfo1.put("remark", "vk marked");
        createPayerReqBodyInfo1.put("smsUseYn", "Y");
        createPayerReqBodyInfo1.put("zaloUseYn", "N");

        createPayerReqBodyInfo2.put("payerNo", "");
        createPayerReqBodyInfo2.put("payerName", String.valueOf(faker.team().name()));
        createPayerReqBodyInfo2.put("phoneNo", "03" + faker.number().randomNumber(8, true));
        createPayerReqBodyInfo2.put("email", "");
        createPayerReqBodyInfo2.put("remark", "vk marked");
        createPayerReqBodyInfo2.put("smsUseYn", "Y");
        createPayerReqBodyInfo2.put("zaloUseYn", "N");

        createPayerReqBodyData.put(createPayerReqBodyInfo1);
        createPayerReqBodyData.put(createPayerReqBodyInfo2);

        createPayerReqBody.put("data", createPayerReqBodyData);

        String reqBody = createPayerReqBody.toString();
        System.out.println(reqBody);*/

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

    }

}
