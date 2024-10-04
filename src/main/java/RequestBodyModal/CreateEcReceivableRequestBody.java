package RequestBodyModal;

import Commons.SampleData;

import java.util.ArrayList;
import java.util.List;

public class CreateEcReceivableRequestBody {
    public List<Data> data;
    public CreateEcReceivableRequestBody() {
        this.data = new ArrayList<>();
        this.data.add(new Data());
    }

    public class Data{
        public String motherAccntNo = SampleData.motherAccntNo;
        public String payerNo = SampleData.payerNo;
        public String ecollectionCd = SampleData.originalEcc;
        public String ecollectionCdHdNm = SampleData.ecollectionCdName;
        public String restrictionType = SampleData.restrictionType;
        public int depositAmt = SampleData.depositAmt;
        public String payableStartDt = SampleData.payableStartDt;
        public String payableEndDt = SampleData.payableEndDt;
        public String payableStartTm = "";
        public String payableEndTm = "";
        public String payDtTmCd = SampleData.payDtTmCd;

    }
}
