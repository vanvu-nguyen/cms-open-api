package test.test;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CmsTest extends BaseTest {

    @Test
    public void TC_01_Admin_Login() {

    }

    @Test
    public void TC_02_Add_Department() {

    }

    @Test
    public void TC_03_Add_User() {

    }

    @Test
    public static void main(String[] args) {
        RequestSpecification request = given();
        request.baseUri("https://uat-bidv-api.infocms.com.vn");
        request.basePath("/auth/v1/login");
        request.header(new Header("accept", "*/*"));
        request.header(new Header("Content-Type", "application/json"));
        String authReqBody = "{\n" +
                "  \"password\": 12345,\n" +
                "  \"userId\": \"bidvtest\"\n" +
                "}";
        Response response = request.body(authReqBody).post();
        response.prettyPrint();
    }
}
