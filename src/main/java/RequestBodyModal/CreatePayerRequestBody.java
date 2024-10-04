package RequestBodyModal;

import Commons.SampleData;

import java.util.ArrayList;
import java.util.List;

public class CreatePayerRequestBody {

    public List<Data> data;

    public CreatePayerRequestBody() {
        this.data = new ArrayList<>();
        this.data.add(new Data());
    }

    public class Data {

        public String payerNo = "";
        public String payerName = SampleData.payerName;
        public String phoneNo = SampleData.phoneNo;
        public String smsUseYn = SampleData.smsUse;
        public String zaloUseYn = SampleData.zaloUse;
        public String email = "";
        public String remark = SampleData.remark;

    }
}
