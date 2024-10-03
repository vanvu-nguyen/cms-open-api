package ResponseBodyModal;

public class LoginEngineSuccessResponseBody {
    public String resultCode;
    public String resultDesc;
    public Data data;
    public static class Data {
        public String accessToken;
    }
}
