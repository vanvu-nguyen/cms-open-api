package RequestBodyModal;

import PGPHandler.ChecksumGenerator;

public class LoginEngineRequestBody {
    public String userId;
    public String password;
    public String checksum;

    public LoginEngineRequestBody() {
        this.userId = "Ymlkdl90ZXN0";
        this.password = "MTIzNDU2NzhAMzQ1QXM=";
        this.checksum = ChecksumGenerator.getLoginChecksum("Ymlkdl90ZXN0", "MTIzNDU2NzhAMzQ1QXM=");
    }
}
