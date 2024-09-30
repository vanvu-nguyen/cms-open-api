package RequestBodyModal;

public class PayerCreateReq {

    public String payerNo = "";
    public String payerName;
    public String phoneNo;
    public String smsUseYn = "";
    public String zaloUseYn = "";
    public String email = "";
    public String remark = "";


    public PayerCreateReq(String payerName, String phoneNo) {
        this.payerName = payerName;
        this.phoneNo = phoneNo;
    }

}
