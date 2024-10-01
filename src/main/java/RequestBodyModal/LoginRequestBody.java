package RequestBodyModal;

public class LoginRequestBody {

    public Data data;
    public LoginRequestBody() {
        this.data = new Data("huytest", "17e8ffee6a261042f6eb26f51ce89d10");
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
