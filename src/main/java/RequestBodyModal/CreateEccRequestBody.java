package RequestBodyModal;

import Commons.SampleData;

import java.util.ArrayList;
import java.util.List;

public class CreateEccRequestBody {

    public List<Data> data;
    public CreateEccRequestBody() {
        this.data = new ArrayList<>();
        this.data.add(new Data());
    }

    public static class Data {
        public String motherAccntNo = SampleData.motherAccntNo;
        public String ecollectionCdName = SampleData.ecollectionCdName;
        public String payerNo = SampleData.payerNo;
    }

}
