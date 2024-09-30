package ModuleList;

import PGPHandler.IPlusJsonParser;
import PGPHandler.PgpEncryptionUtil;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.openpgp.PGPException;
import org.jose4j.base64url.Base64;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PayerModule {

    public PayerModule() {
    }

    public <T> String getEncryptData(T data, String publicKey) throws PGPException, IOException {
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
        return Base64.encode(pgpEncryptionUtil.encrypt(bodyOpenPGP.getBytes(StandardCharsets.UTF_8), publicKey)); // maybe error occur here
    }
}
