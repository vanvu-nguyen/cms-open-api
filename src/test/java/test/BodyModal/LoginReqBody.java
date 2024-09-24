package test.BodyModal;

public class LoginReqBody {

    public Data data;

    public LoginReqBody(String masterId, String clientId) {
        this.data = new Data(masterId, clientId);
    }

    public class Data {
        public String masterId;
        public String clientId;

        public Data(String masterId, String clientId) {
            this.masterId = masterId;
            this.clientId = clientId;
        }
    }
}
