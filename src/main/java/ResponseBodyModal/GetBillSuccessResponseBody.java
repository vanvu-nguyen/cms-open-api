package ResponseBodyModal;

import java.util.List;

public class GetBillSuccessResponseBody {
    public String resultCode;
    public String resultDesc;
    public String serviceId;
    public String motherAccount;
    public String customerId;
    public String customerName;
    public String customerAddr;
    public List<Data> data;

    public static class Data {
        public String type;
        public int amount;
        public String billId;
    }

}
