package RequestBodyModal;

import java.util.ArrayList;
import java.util.List;

public class CreatePayerReq {

    public List<Payer> data;

    public CreatePayerReq() {
        this.data = new ArrayList<>();
    }

    public static class Payer {
        public String payerNo;
        public String payerName;
        public String phoneNo;
        public String smsUseYn;
        public String zaloUseYn;
        public String email;
        public String remark;

        public Payer(String payerNo, String payerName, String phoneNo, String smsUseYn, String zaloUseYn, String email, String remark) {
            this.payerNo = payerNo;
            this.payerName = payerName;
            this.phoneNo = phoneNo;
            this.smsUseYn = smsUseYn;
            this.zaloUseYn = zaloUseYn;
            this.email = email;
            this.remark = remark;
        }
    }

}
