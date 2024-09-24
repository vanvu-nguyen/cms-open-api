package Commons;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDateTime;

public class BaseTest extends RequestCapability {

    protected String accessToken;
    protected int refreshIdx;
    protected LocalDateTime expDateTime;

    protected void getAccessToken() throws JsonProcessingException {
//        RequestSpecification request = given();
//        request.baseUri("https://api-gw.infocms.com.vn/infocms/bidv/external-api/v1.0");
//        request.basePath("/ocms/v2/auth/login");
//        request.header(new Header("accept", "*/*"));
//        request.header(new Header("Content-Type", "application/json"));
//        LoginPM login = new LoginPM("N",12345, "vunguyen");
//        String loginBody = new Gson().toJson(login);
//        Response response = request.body(loginBody).post();
//
//        Map<String, Map<String, String>> firstLvlKeyStr = JsonPath.from(response.asString()).get();
//        Map<String, String> dataKeyString = firstLvlKeyStr.get("data");
//        this.accessToken = dataKeyString.get("accessToken");
//
//        Map<String, Map<String, Integer>> firstLvlKeyInt = JsonPath.from(response.asString()).get();
//        Map<String, Integer> dataKeyInt = firstLvlKeyInt.get("data");
//        this.refreshIdx = dataKeyInt.get("refreshIdx");
//
//        DecodedJWT decodedJWT = JWT.decode(accessToken);
//        String payload = decodedJWT.getPayload();
//        byte[] decodedBytes = Base64.getDecoder().decode(payload);
//        String jsonPayload = new String(decodedBytes);
//        System.out.println(jsonPayload);
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Integer> jsonMap = mapper.readValue(jsonPayload, Map.class);
//        int expiredTimeStamp = jsonMap.get("exp");
//
//        this.expDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(expiredTimeStamp), ZoneId.systemDefault());
//        System.out.println(expDateTime);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDate = dateTime.format(formatter);
//        System.out.println("Formatted date: " + formattedDate);
    }
}
