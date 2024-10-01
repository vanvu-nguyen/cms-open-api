package Commons;

import RequestBodyModal.CreateEcReceivableRequestBody;
import RequestBodyModal.CreateEccRequestBody;
import RequestBodyModal.CreatePayerRequestBody;
import RequestBodyModal.LoginRequestBody;

public class RequestBodyGenerator {
    public static FinalRequestBody getFinalRequestBody(String data) {
        return new FinalRequestBody(data);
    }
    public static LoginRequestBody getLoginRequestBody() {
        return new LoginRequestBody();
    }
    public static CreatePayerRequestBody getCreatePayerRequestBody() {
        return new CreatePayerRequestBody();
    }
    public static CreateEccRequestBody getCreateEccRequestBody(String payerNo) {
        return new CreateEccRequestBody(payerNo);
    }
    public static CreateEcReceivableRequestBody getCreateEcReceivableRequest(String payerNo, String ecc) {
        return new CreateEcReceivableRequestBody(payerNo, ecc);
    }
}
