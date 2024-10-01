package RequestBodyModal;

import java.util.ArrayList;
import java.util.List;

public class CreateEcReceivableRequestBody {
    public List<Data> data;
    public CreateEcReceivableRequestBody(String payerNo, String ecc) {
        this.data = new ArrayList<>();
        this.data.add(new Data(payerNo, ecc));
    }

    public class Data{
        public String motherAccntNo = "7834444422344";
        public String payerNo;
        public String ecollectionCd;
        public String ecollectionCdHdNm = "";
        public String restrictionType = "0";
        public int depositAmt = 200000;
        public String payableStartDt = "20241205";
        public String payableEndDt = "20241207";
        public String payableStartTm = "";
        public String payableEndTm = "";
        public String payDtTmCd = "0";

        public Data(String payerNo, String ecc) {
            this.payerNo = payerNo;
            this.ecollectionCd = ecc;
        }
    }
}
