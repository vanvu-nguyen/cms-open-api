package RequestBodyModal;

public class LoginReqEncrypt {
    public String data;

    public LoginReqEncrypt(String loginJSONBodyEncryptedStr) {
        this.data = loginJSONBodyEncryptedStr;
    }

}
