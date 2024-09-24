package Commons;

import io.restassured.http.Header;

public class RequestCapability {

    protected static final String EMAIL = "nv.vu29@gmail.com";
    protected static final String TOKEN = "ATATT3xFfGF0oAFbdhWeZKwUn8-aHRiW5LLxuoaN0XivL7IIIMuNMPwOaHhqF11nDMSdmRtqBkhZvXF_wVPgBvDT-iIz87IAcHCgg5QJ3ACMxnhdNddEb_Lrt039xwpOzPp6b5Qi_-RrA2WbDV0bnac7WUiWv2feiNJKNhLyv9wotiLATx8hN-M=2D3B214F";
    protected static final Header DEFAULT_HEADER = new Header("Content-type", "application/json; charset=UTF-8");
    protected static final Header ACCEPT_JSON_HEADER = new Header("Accept", "application/json");

    public static Header getAuthentication(String encodedCredString) {
        return new Header("Authorization", "Basic ".concat(encodedCredString));
    }

    
}
