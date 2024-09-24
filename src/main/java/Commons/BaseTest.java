package Commons;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import BodyModal.Login;
import modal.RequestCapability;
import untils.AuthenticationHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static io.restassured.RestAssured.given;

public class BaseTest extends RequestCapability {

    protected String accessToken;
    protected int refreshIdx;
    protected LocalDateTime expDateTime;
    protected RequestSpecification request;
    protected String encodedCredStr;
    protected String projectKey;
    protected String baseUri;

    protected void getAccessTokenOld() throws JsonProcessingException {
        RequestSpecification request = given();
        request.baseUri("https://uat-bidv-admin-api.infocms.com.vn");
        request.basePath("/auth/v1/login");
        request.header(new Header("accept", "*/*"));
        request.header(new Header("Content-Type", "application/json"));
        Login login = new Login("N",12345, "vunguyen");
        String loginBody = new Gson().toJson(login);
        Response response = request.body(loginBody).post();

        Map<String, Map<String, String>> firstLvlKeyStr = JsonPath.from(response.asString()).get();
        Map<String, String> dataKeyString = firstLvlKeyStr.get("data");
        this.accessToken = dataKeyString.get("accessToken");

        Map<String, Map<String, Integer>> firstLvlKeyInt = JsonPath.from(response.asString()).get();
        Map<String, Integer> dataKeyInt = firstLvlKeyInt.get("data");
        this.refreshIdx = dataKeyInt.get("refreshIdx");

        DecodedJWT decodedJWT = JWT.decode(accessToken);
        String payload = decodedJWT.getPayload();
        byte[] decodedBytes = Base64.getDecoder().decode(payload);
        String jsonPayload = new String(decodedBytes);
        System.out.println(jsonPayload);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Integer> jsonMap = mapper.readValue(jsonPayload, Map.class);
        int expiredTimeStamp = jsonMap.get("exp");

        this.expDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(expiredTimeStamp), ZoneId.systemDefault());
        System.out.println(expDateTime);

        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateTime.format(formatter);
        System.out.println("Formatted date: " + formattedDate);*/
    }


    //======================
    @BeforeSuite
    public void beforeSuite() {
        encodedCredStr = AuthenticationHandler.encodedCredStr(EMAIL, TOKEN);
        baseUri = "https://sdetvu.atlassian.net";
        projectKey = "SDET";
    }

    @BeforeTest
    public void beforeTest() {
        request = given();
        request.baseUri(baseUri);
        request.header(DEFAULT_HEADER);
        request.header(ACCEPT_JSON_HEADER);
        request.header(getAuthentication(encodedCredStr));
    }

    protected void getAccessToken() throws JsonProcessingException {
        RequestSpecification request = given();
        request.baseUri("https://api-gw.infocms.com.vn/infocms/bidv/external-api/v1.0");
        request.basePath("/ocms/v2/auth/login");
        request.header(new Header("accept", "*/*"));
        request.header(new Header("Content-Type", "application/json"));
        Login login = new Login("N",12345, "vunguyen");
        String loginBody = new Gson().toJson(login);
        Response response = request.body(loginBody).post();

        Map<String, Map<String, String>> firstLvlKeyStr = JsonPath.from(response.asString()).get();
        Map<String, String> dataKeyString = firstLvlKeyStr.get("data");
        this.accessToken = dataKeyString.get("accessToken");

        Map<String, Map<String, Integer>> firstLvlKeyInt = JsonPath.from(response.asString()).get();
        Map<String, Integer> dataKeyInt = firstLvlKeyInt.get("data");
        this.refreshIdx = dataKeyInt.get("refreshIdx");

        DecodedJWT decodedJWT = JWT.decode(accessToken);
        String payload = decodedJWT.getPayload();
        byte[] decodedBytes = Base64.getDecoder().decode(payload);
        String jsonPayload = new String(decodedBytes);
        System.out.println(jsonPayload);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Integer> jsonMap = mapper.readValue(jsonPayload, Map.class);
        int expiredTimeStamp = jsonMap.get("exp");

        this.expDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(expiredTimeStamp), ZoneId.systemDefault());
        System.out.println(expDateTime);

        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateTime.format(formatter);
        System.out.println("Formatted date: " + formattedDate);*/
    }


}
