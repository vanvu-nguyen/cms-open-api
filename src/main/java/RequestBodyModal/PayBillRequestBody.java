package RequestBodyModal;

import Commons.SampleData;
import PGPHandler.ChecksumGenerator;

public class PayBillRequestBody {
    public String transId;
    public String transDate;
    public String customerId;
    public String serviceId;
    public String channel;
    public String billId;
    public int amount;
    public String refNum;
    public String checksum;

    public PayBillRequestBody() {
        this.transId = SampleData.transId;
        this.transDate = SampleData.transDate;
        this.customerId = SampleData.cuttedPrefixEcc;
        this.serviceId = SampleData.serviceId;
        this.channel = SampleData.channel;
        this.billId = SampleData.billId;
        this.amount = SampleData.payAmount;
        this.refNum = SampleData.refNum;
        this.checksum = ChecksumGenerator.getPaybillChecksum();
    }
}
