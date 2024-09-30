package RequestBodyModal;

public class LoginReq {

    public Data data;

    public LoginReq(String masterId, String clientId) {
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
