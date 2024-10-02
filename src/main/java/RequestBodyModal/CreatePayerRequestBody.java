package RequestBodyModal;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CreatePayerRequestBody {

    public List<Data> data;

    public CreatePayerRequestBody() {
        this.data = new ArrayList<>();
        this.data.add(new Data());
    }

    public class Data {

        public String payerNo = "";
        public String payerName = String.valueOf(new Faker(new Locale("en_US")).team().name());
        public String phoneNo = "03" + new Faker(new Locale("en_US")).number().randomNumber(8, true);
        public String smsUseYn = "Y";
        public String zaloUseYn = "Y";
        public String email = "";
        public String remark = "VK marked";

    }
}
