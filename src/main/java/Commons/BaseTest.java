package Commons;

import PGPHandler.IPlusJsonParser;
import PGPHandler.KeyContainer;
import PGPHandler.PgpDecryptionUtil;
import PGPHandler.PgpEncryptionUtil;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.openpgp.PGPException;
import org.jose4j.base64url.Base64;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BaseTest extends RequestCapability {


    // FOR DATA ENCRYPTION
    public static <T> String getEncryptData(T data) throws PGPException, IOException {
        String publicKey = KeyContainer.PUBLIC_KEY;
        String bodyOpenPGP = IPlusJsonParser.getJsonStringFromObject(data);
        if(bodyOpenPGP == null) {
            return null;
        }
        PgpEncryptionUtil pgpEncryptionUtil = PgpEncryptionUtil.builder()
                .armor(true)
                .compressionAlgorithm(CompressionAlgorithmTags.ZIP)
                .symmetricKeyAlgorithm(SymmetricKeyAlgorithmTags.AES_128)
                .withIntegrityCheck(true)
                .build();
        return Base64.encode(pgpEncryptionUtil.encrypt(bodyOpenPGP.getBytes(StandardCharsets.UTF_8), publicKey));
    }

    // FOR DATA DECRYPTION
    public static <T> T getBodyDecrypt(String encryptedData, Class<T> clazz) throws PGPException, IOException {
        PgpDecryptionUtil pgpDecryptionUtil = new PgpDecryptionUtil(KeyContainer.PRIVATE_KEY, KeyContainer.PASS_CODE);
        return IPlusJsonParser.getObjectFromByteArray(pgpDecryptionUtil.decrypt(Base64.decode(encryptedData)), clazz);
    }

}
