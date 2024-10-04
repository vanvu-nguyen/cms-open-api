package RequestBodyModal;

import Commons.SampleData;
import PGPHandler.ChecksumGenerator;

public class GetBillRequestBody {
    public String customerId;
    public String serviceId;
    public String channel;
    public String checksum;

    public GetBillRequestBody() {
        this.customerId = SampleData.cuttedPrefixEcc;
        this.serviceId = SampleData.serviceId;
        this.channel = SampleData.channel;
        this.checksum = ChecksumGenerator.getGetbillChecksum();
    }
}
