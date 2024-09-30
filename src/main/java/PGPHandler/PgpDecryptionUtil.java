package PGPHandler;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import org.apache.commons.io.IOUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Iterator;
import java.util.Objects;

public class PgpDecryptionUtil {
    private final char[] passCode;
    private final PGPSecretKeyRingCollection pgpSecretKeyRingCollection;

    public PgpDecryptionUtil(InputStream privateKeyIn, String passCode) {
        try {
            this.passCode = passCode.toCharArray();
            this.pgpSecretKeyRingCollection = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(privateKeyIn), new JcaKeyFingerprintCalculator());
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public PgpDecryptionUtil(String privateKeyStr, String passCode) throws IOException, PGPException {
        this(IOUtils.toInputStream(privateKeyStr, StandardCharsets.UTF_8), passCode);
    }

    private PGPPrivateKey findSecretKey(long keyID) throws PGPException {
        PGPSecretKey pgpSecretKey = this.pgpSecretKeyRingCollection.getSecretKey(keyID);
        return pgpSecretKey == null ? null : pgpSecretKey.extractPrivateKey((new JcePBESecretKeyDecryptorBuilder()).setProvider("BC").build(this.passCode));
    }

    public void decrypt(InputStream encryptedIn, OutputStream clearOut) throws PGPException, IOException {
        encryptedIn = PGPUtil.getDecoderStream(encryptedIn);
        JcaPGPObjectFactory pgpObjectFactory = new JcaPGPObjectFactory(encryptedIn);
        Object obj = pgpObjectFactory.nextObject();
        PGPEncryptedDataList pgpEncryptedDataList = obj instanceof PGPEncryptedDataList ? (PGPEncryptedDataList)obj : (PGPEncryptedDataList)pgpObjectFactory.nextObject();
        PGPPrivateKey pgpPrivateKey = null;
        PGPPublicKeyEncryptedData publicKeyEncryptedData = null;

        for(Iterator<PGPEncryptedData> encryptedDataItr = pgpEncryptedDataList.getEncryptedDataObjects(); pgpPrivateKey == null && encryptedDataItr.hasNext(); pgpPrivateKey = this.findSecretKey(publicKeyEncryptedData.getKeyID())) {
            publicKeyEncryptedData = (PGPPublicKeyEncryptedData)encryptedDataItr.next();
        }

        if (Objects.isNull(publicKeyEncryptedData)) {
            throw new PGPException("Could not generate PGPPublicKeyEncryptedData object");
        } else if (pgpPrivateKey == null) {
            throw new PGPException("Could Not Extract private key");
        } else {
            CommonOpenPGPUtils.decrypt(clearOut, pgpPrivateKey, publicKeyEncryptedData);
        }
    }

    public byte[] decrypt(byte[] encryptedBytes) throws PGPException, IOException {
        ByteArrayInputStream encryptedIn = new ByteArrayInputStream(encryptedBytes);
        ByteArrayOutputStream clearOut = new ByteArrayOutputStream();
        this.decrypt(encryptedIn, clearOut);
        return clearOut.toByteArray();
    }

    static {
        if (Objects.isNull(Security.getProvider("BC"))) {
            Security.addProvider(new BouncyCastleProvider());
        }

    }
}

