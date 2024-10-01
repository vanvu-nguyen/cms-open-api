package ResponseBodyModal;

import java.util.List;

public class CreateEccResponseBody {
    public List<SuccessList> succList;
    public List<ErrorList> errorList;

    public static class SuccessList {
        public String payerNo;
        public String motherAccntNo;
        public String ecollectionCd;
    }

    public static class ErrorList {
        public String payerNo;
        public String motherAccntNo;
        public String ecollectionCdName;
        public String errorCode;
        public String errorMsg;
        public int reqIdx;
    }
}
