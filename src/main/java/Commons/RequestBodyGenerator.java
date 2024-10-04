package Commons;

import RequestBodyModal.*;

public class RequestBodyGenerator {
    public static FinalRequestBody getFinalRequestBody(String encryptedBody) {
        return new FinalRequestBody(encryptedBody);
    }
    public static LoginRequestBody getLoginRequestBody() {
        return new LoginRequestBody();
    }
    public static CreatePayerRequestBody getCreatePayerRequestBody() {
        return new CreatePayerRequestBody();
    }
    public static CreateEccRequestBody getCreateEccRequestBody() {
        return new CreateEccRequestBody();
    }
    public static CreateEcReceivableRequestBody getCreateEcReceivableRequest() {
        return new CreateEcReceivableRequestBody();
    }
    public static LoginEngineRequestBody getLoginEngineRequestBody() {
        return new LoginEngineRequestBody();
    }
    public static GetBillRequestBody getGetBillRequestBody() {
        return new GetBillRequestBody();
    }
    public static PayBillRequestBody getPayBillRequestBody() {
        return new PayBillRequestBody();
    }
}
