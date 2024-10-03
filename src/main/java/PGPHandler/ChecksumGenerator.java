package PGPHandler;

import Commons.RequestCapability;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ChecksumGenerator {

    private static final String SECRET_KEY = "7476576532692068";

    public static String getLoginChecksum(String userId, String password) {
        try {
            // Decode Base64 userId and password
            String decUserId = new String(Base64.getDecoder().decode(userId), StandardCharsets.UTF_8);
            String decPassword = new String(Base64.getDecoder().decode(password), StandardCharsets.UTF_8);

            // Combine userId, password, and secret key
            String sourceData = decUserId + "|" + decPassword + "|" + SECRET_KEY;

            // Generate HMAC SHA256 hash
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256Hmac.init(secretKeySpec);

            byte[] hmacData = sha256Hmac.doFinal(sourceData.getBytes(StandardCharsets.UTF_8));

            // Encode the HMAC result to Base64
            return Base64.getEncoder().encodeToString(hmacData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getGetbillChecksum(String customerId) {
        try {

            String requestId = "test1234";
            String serviceId = "huych";
            String channel = "E";

            // Combine userId, password, and secret key
            String sourceData = requestId + "|" + customerId + "|" + serviceId + "|" + channel + "|" + SECRET_KEY;

            // Generate HMAC SHA256 hash
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256Hmac.init(secretKeySpec);

            byte[] hmacData = sha256Hmac.doFinal(sourceData.getBytes(StandardCharsets.UTF_8));

            // Encode the HMAC result to Base64
            return Base64.getEncoder().encodeToString(hmacData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPaybillChecksum() {
        try {

            String requestId = "test1234";
            String transId = RequestCapability.transId;
            String transDate = RequestCapability.TRANS_DATE;
            String customerId = RequestCapability.cuttedPrefixEcc;
            String serviceId = "huych";
            String channel = "E";
            String billId = RequestCapability.billId;
            int amount = 5000;
            String refNum = RequestCapability.refNum;

            // Combine userId, password, and secret key
            String sourceData = requestId + "|" + transId + "|" + transDate + "|" + customerId + "|" + serviceId
                    + "|" + channel + "|" + billId + "|" + amount + "|" + refNum + "|" + SECRET_KEY;

            // Generate HMAC SHA256 hash
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256Hmac.init(secretKeySpec);

            byte[] hmacData = sha256Hmac.doFinal(sourceData.getBytes(StandardCharsets.UTF_8));

            // Encode the HMAC result to Base64
            return Base64.getEncoder().encodeToString(hmacData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

