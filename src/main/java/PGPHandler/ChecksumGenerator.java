package PGPHandler;

import Commons.SampleData;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ChecksumGenerator {

    private static final String SECRET_KEY = "7476576532692068";

    public static String getLoginChecksum() {
        try {
            String userId = SampleData.ENGINE_USER_ID;
            String password = SampleData.ENGINE_PASSWORD;

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

    public static String getGetbillChecksum() {
        try {
            String customerId = SampleData.cuttedPrefixEcc;
            String requestId = SampleData.requestId;
            String serviceId = SampleData.serviceId;
            String channel = SampleData.channel;

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

            String requestId = SampleData.requestId;
            String transId = SampleData.transId;
            String transDate = SampleData.transDate;
            String customerId = SampleData.cuttedPrefixEcc;
            String serviceId = SampleData.serviceId;
            String channel = SampleData.channel;
            String billId = SampleData.billId;
            int amount = SampleData.payAmount;
            String refNum = SampleData.refNum;

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

