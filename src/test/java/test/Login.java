package test;

import Module.LoginModule;
import Commons.ModuleGenerator;
import org.testng.annotations.Test;

public class Login {

    private LoginModule loginModule = ModuleGenerator.getLoginModule();
    private String loginBodyStr;
    @Test
    public void TC_01_EncryptLoginBody() {
        loginBodyStr = loginModule.getLoginJSONBody();
        loginModule.encryptJSONBody();
    }

    @Test
    public void TC_02_GetAccessToken() {
        loginModule.setReqHd();
        loginModule.getAccessToken();
    }

}
