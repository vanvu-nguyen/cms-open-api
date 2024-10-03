package RequestBodyModal;

import Commons.RequestCapability;
import PGPHandler.ChecksumGenerator;

public class GetBillRequestBody {
    public String customerId;
    public String serviceId;
    public String channel;
    public String checksum;

    public GetBillRequestBody() {
        this.customerId = RequestCapability.cuttedPrefixEcc;
        this.serviceId = "huych";
        this.channel = "E";
        this.checksum = ChecksumGenerator.getGetbillChecksum(RequestCapability.cuttedPrefixEcc);
    }
}
