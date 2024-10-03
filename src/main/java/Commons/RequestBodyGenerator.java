package Commons;

import RequestBodyModal.*;

public class RequestBodyGenerator {
    public static FinalRequestBody getFinalRequestBody() {
        return new FinalRequestBody();
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
