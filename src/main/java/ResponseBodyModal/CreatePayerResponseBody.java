package ResponseBodyModal;

import java.util.List;

public class CreatePayerResponseBody {
    public List<SuccessList> succList;
    public List<ErrorList> errorList;

    public static class SuccessList {
        public String payerNo;
        public String payerName;
        public String phoneNo;
        public String email;
        public String remark;
    }

    public static class ErrorList {
        public String payerNo;
        public String payerName;
        public String phoneNo;
        public String email;
        public String remark;
        public String smsUseYn;
        public String zaloUseYn;
        public String errorCode;
        public String errorMsg;
        public int reqIdx;

    }
}
