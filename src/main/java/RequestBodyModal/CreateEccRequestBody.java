package RequestBodyModal;

import java.util.ArrayList;
import java.util.List;

public class CreateEccRequestBody {

    public List<Data> data;
    public CreateEccRequestBody(String payerNo) {
        this.data = new ArrayList<>();
        this.data.add(new Data(payerNo));
    }

    public class Data{
        public String motherAccntNo = "7834444422344";
        public String ecollectionCdName = "VK holder name";
        public String payerNo;

        public Data(String payerNo) {
            this.payerNo = payerNo;
        }
    }
}
