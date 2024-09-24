package Module;

import RequestBodyModal.LoginReq;
import com.google.gson.Gson;

public class LoginModule {

    public LoginModule() {
    }

    public String getLoginJSONBody() {
        LoginReq loginBodyObj = new LoginReq(System.getenv("userId"), System.getenv("clientId"));
        return new Gson().toJson(loginBodyObj);
    }

    public void encryptJSONBody() {
    }

    public void setReqHd() {
    }

    public void getAccessToken() {
    }
}
