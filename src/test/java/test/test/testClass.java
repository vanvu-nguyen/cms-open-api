package test.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import test.BodyModal.Department_Insert;
import test.BodyModal.User_Insert;

import static io.restassured.RestAssured.given;

public class testClass extends BaseTest{

    @Test
    public void getAT() throws JsonProcessingException {

        getAccessTokenOld();

        RequestSpecification request = given();
        request.baseUri("https://uat-bidv-admin-api.infocms.com.vn");
        request.basePath("/api/v1/Z014/insert");
        request.header(new Header("accept", "*/*"));
        request.header(new Header("Content-Type", "application/json"));
        request.header(new Header("token", accessToken));
        Department_Insert departmentInsert = new Department_Insert("vunguyen","1991-04-15 8:30:00 168", "en", "1504199112348", "Sample Department");
        String departmentInsertBody = new Gson().toJson(departmentInsert);
        Response response = request.body(departmentInsertBody).post();
        response.prettyPrint();

        request.basePath("/api/v1/Z015/insert");
        User_Insert userInsert = new User_Insert("vunguyen", "1991-04-15 8:30:00 168", "en", "sanpleId", "sampleUser", "sampleCode", "A", 987789987, "0987789987@gmail.com", "sampleReg", "1789789");
        String userInsertBody = new Gson().toJson(userInsert);
        response = request.body(userInsertBody).post();
        response.prettyPrint();


    }
}
