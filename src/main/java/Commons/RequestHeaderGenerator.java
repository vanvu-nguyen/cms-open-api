package Commons;

import BasePath.BasePathList;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestHeaderGenerator {
    public static RequestSpecification getLoginRequestHeader() throws Exception {
        RequestSpecification request = given();
        request.baseUri(RequestCapability.OPEN_API_BASE_URL);
        request.basePath(BasePathList.OPEN_API_LOGIN);
        request.header(RequestCapability.AUTHORIZATION_HEADER_PARAMETERS);
        request.header(RequestCapability.getHMACSignatureHeader());
        request.header(RequestCapability.BANK_CODE);
        request.header(RequestCapability.CONTENT_TYPE);
        return request;
    }

    public static RequestSpecification getCustomRequestHeader(String basePath, String accessToken) {
        RequestSpecification request = given();
        request.baseUri(RequestCapability.OPEN_API_BASE_URL);
        request.basePath(basePath);
        request.header(RequestCapability.getTokenHeader(accessToken));
        request.header(RequestCapability.CONTENT_TYPE);
        request.header(RequestCapability.AUTHORIZATION_HEADER_PARAMETERS);
        request.header(RequestCapability.BANK_CODE);
        return request;
    }

    public static RequestSpecification getEngineLoginRequestHeader() {
        RequestSpecification request = given();
        request.baseUri(RequestCapability.ENGINE_BASE_URL);
        request.basePath(BasePathList.ENGINE_LOGIN);
        request.header(RequestCapability.CONTENT_TYPE);
        return request;
    }

    public static RequestSpecification getGetbillRequestHeader() {
        RequestSpecification request = given();
        request.baseUri(RequestCapability.ENGINE_BASE_URL);
        request.basePath(BasePathList.GET_BILL);
        request.header(RequestCapability.getTokenHeader(RequestCapability.engineAccessToken));
        request.header(RequestCapability.REQUEST_ID);
        request.header(RequestCapability.CONTENT_TYPE);
        return request;
    }

    public static RequestSpecification getPaybillRequestHeader() {
        RequestSpecification request = given();
        request.baseUri(RequestCapability.ENGINE_BASE_URL);
        request.basePath(BasePathList.PAY_BILL);
        request.header(RequestCapability.getTokenHeader(RequestCapability.engineAccessToken));
        request.header(RequestCapability.REQUEST_ID);
        request.header(RequestCapability.CONTENT_TYPE);
        return request;
    }
}
