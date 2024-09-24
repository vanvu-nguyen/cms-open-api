package test.test;

import com.google.gson.Gson;
import test.BodyModal.LoginReqBody;

public class OpenApiTesting {
    public static void main(String[] args) {
        LoginReqBody loginReqBd = new LoginReqBody("abc", "123");
        String loginReqBdStr = new Gson().toJson(loginReqBd);
    }
}
