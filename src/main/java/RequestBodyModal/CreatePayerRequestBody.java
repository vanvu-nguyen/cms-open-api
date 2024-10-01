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

        Faker faker = new Faker(new Locale("en_US"));

        public String payerNo = "";
        public String payerName = String.valueOf(faker.team().name());
        public String phoneNo = "03" + faker.number().randomNumber(8, true);
        public String smsUseYn = "Y";
        public String zaloUseYn = "Y";
        public String email = "";
        public String remark = "VK marked";

    }
}
