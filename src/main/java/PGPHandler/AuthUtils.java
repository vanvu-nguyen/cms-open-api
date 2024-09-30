package PGPHandler;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class AuthUtils {

    private static final String CLIENT_KEY = "17e8ffee6a261042f6eb26f51ce89d10";
    private static final String SECRET_KEY = "af07c11a8e97c530be9539229bb11e90379f09d2e71d85d0aa78e30448878deb";

    public static String getAuthHeader() throws Exception {
        // Get today's date in 'sv' format (yyyyMMdd)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());

        // Concatenate CLIENT_KEY, today's date, and SECRET_KEY
        String sourceData = CLIENT_KEY + today + SECRET_KEY;

        // Create HmacSHA512 hash
        Mac mac = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        mac.init(secretKeySpec);
        byte[] hmacSha512Bytes = mac.doFinal(sourceData.getBytes(StandardCharsets.UTF_8));

        // Encode hash using Base64
        return Base64.getEncoder().encodeToString(hmacSha512Bytes);
    }

}
