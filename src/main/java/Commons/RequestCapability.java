package Commons;

import PGPHandler.AuthUtils;
import io.restassured.http.Header;

public class RequestCapability {

    public static final String BASE_URL = "https://api-gw.infocms.com.vn/infocms/bidv/external-api/v1.0";
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
}
