package Commons;

import com.github.javafaker.Faker;

import java.util.Locale;

public class SampleData {

    // Database accessible info
    public static final String DB_HOST_NAME = "10.100.116.40";
    public static final String DB_NAME = "infocms_bidv";
    public static final String DB_USER_NAME = "infocms_ba_user";
    public static final String DB_PASSWORD = "Inf0#Cm$2050P@";

    // Open API Enterprise credential
    public static final String OPEN_API_MASTER_ID = "huytest";
    public static final String OPEN_API_CLIENT_ID = "17e8ffee6a261042f6eb26f51ce89d10";

    // Engine Bank credential
    public static String ENGINE_USER_ID = "Ymlkdl90ZXN0";
    public static String ENGINE_PASSWORD = "MTIzNDU=";

    // Open API test data
    public static String payerNo;
    public static String payerName = String.valueOf(new Faker(new Locale("en_US")).team().name());
    public static String phoneNo = "03" + new Faker(new Locale("en_US")).number().randomNumber(8, true);
    public static String smsUse = "Y";
    public static String zaloUse = "Y";
    public static String email;
    public static String remark = "VK remark";
    public static String ecollectionCdName = "VK holder name";
    public static String motherAccntNo = "7834444422344";
    public static String originalEcc;
    public static String restrictionType = "0";
    public static int depositAmt = 200000;
    public static String payableStartDt = "20251205";
    public static String payableEndDt = "20251207";
    public static String payDtTmCd = "0";

    // Engine test data
    public static String cuttedPrefixEcc;
    public static String serviceId = "huych";
    public static String channel = "E";
    public static String requestId = "test1234";
    public static String billId;
    public static String transId = String.valueOf(new Faker(new Locale("en_US")).number().randomNumber(7, true));
    public static String transDate = "19910415083000";
    public static int payAmount = 5000;
    public static String refNum = "A" + String.valueOf(new Faker(new Locale("en_US")).number().randomNumber(7, true));

}
