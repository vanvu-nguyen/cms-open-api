package RequestBodyModal;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CreateEccMapPayerReq {
    public List<PayerEccPair> data;

    public CreateEccMapPayerReq() {
        this.data = new ArrayList<>();
    }

    public static class PayerEccPair{

        @Setter @Getter
        public String payerNo;

        @Setter @Getter
        public String motherAccntNo;

        @Setter @Getter
        public String ecollectionCdName;

        public PayerEccPair(String payerNo, String motherAccntNo, String ecollectionCdName) {
            this.payerNo = payerNo;
            this.motherAccntNo = motherAccntNo;
            this.ecollectionCdName = ecollectionCdName;
        }
    }
}
