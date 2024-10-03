package Commons;

import PGPHandler.AuthUtils;
import com.github.javafaker.Faker;
import io.restassured.http.Header;

import java.util.Locale;

public class RequestCapability {

    public static final String OPEN_API_BASE_URL = "https://api-gw.infocms.com.vn/infocms/bidv/external-api/v1.0";
    public static final String ENGINE_BASE_URL = "https://api-gw.infocms.com.vn/infocms/bidv/engine-api/v1.0";
    public static final Header AUTHORIZATION_HEADER_PARAMETERS = new Header("AuthorizationHeaderParameters", "MTdlOGZmZWU2YTI2MTA0MmY2ZWIyNmY1MWNlODlkMTA="); // value is already base64 from CLIENT_ID in DB
    public static Header getHMACSignatureHeader() throws Exception {
        String HMACSignature = AuthUtils.getAuthHeader();
        return new Header("Authorization", HMACSignature);
    }
    public static final Header BANK_CODE = new Header("bankCode", "01202001");
    public static final Header CONTENT_TYPE = new Header("Content-Type", "application/json; charset=UTF-8");
    public static Header getTokenHeader(String accessToken) {
        return new Header("token", accessToken);
    }
    public static final Header REQUEST_ID = new Header("requestid", "test1234");

    public static String originalEcc;
    public static String cuttedPrefixEcc;
    public static String engineAccessToken;
    public static String billId;

    public static String transId = String.valueOf(new Faker(new Locale("en_US")).number().randomNumber(7, true));
    public static final String TRANS_DATE = "19910415083000";
    public static String refNum = "A" + String.valueOf(new Faker(new Locale("en_US")).number().randomNumber(7, true));

}
