package RequestBodyModal;

public class LoginReq {

    public Data data;

    public LoginReq(String userId, String clientId) {
        this.data = new Data(userId, clientId);
    }

    public class Data {
        public String userId;
        public String clientId;

        public Data(String userId, String clientId) {
            this.userId = userId;
            this.clientId = clientId;
        }
    }

}
