package RequestBodyModal;

import Commons.RequestCapability;
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
        this.transId = RequestCapability.transId;
        this.transDate = "19910415083000";
        this.customerId = RequestCapability.cuttedPrefixEcc;
        this.serviceId = "huych";
        this.channel = "E";
        this.billId = RequestCapability.billId;
        this.amount = 5000;
        this.refNum = RequestCapability.refNum;
        this.checksum = ChecksumGenerator.getPaybillChecksum();
    }
}
