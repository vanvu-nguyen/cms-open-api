package Commons;

import io.restassured.http.Header;

public class RequestCapability {

    public static final String BASE_URL = "https://api-gw.infocms.com.vn/infocms/bidv/external-api/v1.0";
    public static final Header CLIENT_KEY = new Header("AuthorizationHeaderParameters", "MTdlOGZmZWU2YTI2MTA0MmY2ZWIyNmY1MWNlODlkMTA="); // value is already base64 from CLIENT_ID in DB
    public static final Header HMAC_AUTH = new Header("Authorization", "bKqv5eK9GDZOds5APiI0JwYO0GhaqLJGWRUQs6K3LpeS6y3EMMAfCdtzFBNXUid8BW+a9oKDBvX8LyFaKA5hlA==");
    public static final Header BANK_CODE = new Header("bankCode", "01202001");
    public static final Header CONTENT_TYPE = new Header("Content-Type", "application/json; charset=UTF-8");

}
