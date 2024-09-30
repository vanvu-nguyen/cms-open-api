package ModuleList;

import BasePath.Authentication;
import Commons.RequestCapability;
import PGPHandler.IPlusJsonParser;
import PGPHandler.PgpDecryptionUtil;
import PGPHandler.PgpEncryptionUtil;
import RequestBodyModal.LoginReq;
import org.jose4j.base64url.Base64;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.openpgp.PGPException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginModule {

    public LoginModule() {
    }

    public LoginReq getLoginJSONBody() {
        return new LoginReq("huytest", "17e8ffee6a261042f6eb26f51ce89d10");
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

    public RequestSpecification getReqHd(RequestSpecification request) {
        request.baseUri(RequestCapability.BASE_URL);
        request.basePath(Authentication.LOGIN);
        request.header(RequestCapability.CLIENT_KEY);
        //request.header(RequestCapability.HMAC_AUTH);
        request.header(RequestCapability.BANK_CODE);
        request.header(RequestCapability.CONTENT_TYPE);
        return request;
    }

    public Response getAccessToken(RequestSpecification request, String loginJSONBodyEncryptedStr) {
        return request.body(loginJSONBodyEncryptedStr).post();
    }

    public <T> T getBodyDecrypt(String privateKey, String passCode, String encrypted, Class<T> clazz) throws PGPException, IOException {
        PgpDecryptionUtil pgpDecryptionUtil = new PgpDecryptionUtil(privateKey, passCode);
        return IPlusJsonParser.getObjectFromByteArray(pgpDecryptionUtil.decrypt(Base64.decode(encrypted)), clazz);
    }

}
