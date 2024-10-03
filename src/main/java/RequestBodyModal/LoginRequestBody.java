package RequestBodyModal;

import Commons.SampleData;

public class LoginRequestBody {

    public Data data;
    public LoginRequestBody() {
        this.data = new Data(SampleData.OPEN_API_MASTER_ID, SampleData.OPEN_API_CLIENT_ID);
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
