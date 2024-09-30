package RequestBodyModal;

import java.util.ArrayList;
import java.util.List;

public class CreateReceivableReq {
    public List<Receivable> data;

    public CreateReceivableReq() {
        this.data = new ArrayList<>();
    }

    public static class Receivable{
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

        public Receivable(String motherAccntNo, String payerNo, String ecollectionCd, String ecollectionCdHdNm, String restrictionType, int depositAmt, String payableStartDt, String payableEndDt, String payableStartTm, String payableEndTm, String payDtTmCd) {
            this.motherAccntNo = motherAccntNo;
            this.payerNo = payerNo;
            this.ecollectionCd = ecollectionCd;
            this.ecollectionCdHdNm = ecollectionCdHdNm;
            this.restrictionType = restrictionType;
            this.depositAmt = depositAmt;
            this.payableStartDt = payableStartDt;
            this.payableEndDt = payableEndDt;
            this.payableStartTm = payableStartTm;
            this.payableEndTm = payableEndTm;
            this.payDtTmCd = payDtTmCd;
        }
    }
}
