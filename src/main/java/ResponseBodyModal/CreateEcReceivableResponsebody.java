package ResponseBodyModal;

import java.util.List;

public class CreateEcReceivableResponsebody {
    public List<SuccessList> succList;
    public List<ErrorList> errorList;

    public static class SuccessList {
        public String ecollectionCd;
        public String payerNo;
        public String receivableId;
    }

    public static class ErrorList {
        public String motherAccntNo;
        public String payerNo;
        public String ecollectionCd;
        public String ecollectionCdHdNm;
        public String restrictionType;
        public int depositAmt;
        public String payableStartDt;
        public String payableEndDt;
        public String payableStartTm;
        public String payableEndTm;
        public String payDtTmCd;
        public String errorCode;
        public String errorMsg;
        public int reqIdx;
    }
}
