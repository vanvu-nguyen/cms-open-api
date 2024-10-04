package RequestBodyModal;

import Commons.SampleData;
import PGPHandler.ChecksumGenerator;

public class LoginEngineRequestBody {
    public String userId;
    public String password;
    public String checksum;

    public LoginEngineRequestBody() {
        this.userId = SampleData.ENGINE_USER_ID;
        this.password = SampleData.ENGINE_PASSWORD;
        this.checksum = ChecksumGenerator.getLoginChecksum();
    }
}
