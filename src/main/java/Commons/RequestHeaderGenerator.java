package Commons;

import BasePath.PathList;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestHeaderGenerator {
    public static RequestSpecification getLoginRequestHeader() throws Exception {
        RequestSpecification request = given();
        request.baseUri(RequestCapability.OPEN_API_BASE_URL);
        request.basePath(PathList.OPEN_API_LOGIN);
        request.header(RequestCapability.AUTHORIZATION_HEADER_PARAMETERS);
        request.header(RequestCapability.getHMACSignatureHeader());
        request.header(RequestCapability.BANK_CODE);
        request.header(RequestCapability.CONTENT_TYPE);
        return request;
    }

    public static RequestSpecification getOpenAPICustomRequestHeader(String basePath) {
        RequestSpecification request = given();
        request.baseUri(RequestCapability.OPEN_API_BASE_URL);
        request.basePath(basePath);
        request.header(RequestCapability.getOpenAPITokenHeader());
        request.header(RequestCapability.CONTENT_TYPE);
        request.header(RequestCapability.AUTHORIZATION_HEADER_PARAMETERS);
        request.header(RequestCapability.BANK_CODE);
        return request;
    }

    public static RequestSpecification getEngineLoginRequestHeader() {
        RequestSpecification request = given();
        request.baseUri(RequestCapability.ENGINE_BASE_URL);
        request.basePath(PathList.ENGINE_LOGIN);
        request.header(RequestCapability.CONTENT_TYPE);
        return request;
    }

    public static RequestSpecification getGetbillRequestHeader() {
        RequestSpecification request = given();
        request.baseUri(RequestCapability.ENGINE_BASE_URL);
        request.basePath(PathList.GET_BILL);
        request.header(RequestCapability.getEngineTokenHeader());
        request.header(RequestCapability.REQUEST_ID);
        request.header(RequestCapability.CONTENT_TYPE);
        return request;
    }

    public static RequestSpecification getPaybillRequestHeader() {
        RequestSpecification request = given();
        request.baseUri(RequestCapability.ENGINE_BASE_URL);
        request.basePath(PathList.PAY_BILL);
        request.header(RequestCapability.getEngineTokenHeader());
        request.header(RequestCapability.REQUEST_ID);
        request.header(RequestCapability.CONTENT_TYPE);
        return request;
    }
}
