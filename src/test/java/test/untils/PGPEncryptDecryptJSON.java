package test.untils;

import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.operator.jcajce.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;

public class PGPEncryptDecryptJSON {

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        // Generate keys for demonstration purposes
        PGPKeyPair keyPair = generateKeyPair();
        PGPPublicKey publicKey = keyPair.getPublicKey();
        PGPPrivateKey privateKey = keyPair.getPrivateKey();

        String json = "{\"name\":\"John Doe\",\"age\":30}";
        byte[] encryptedData = encrypt(json, publicKey);
        String decryptedData = decrypt(encryptedData, privateKey);

        System.out.println("Original JSON: " + json);
        System.out.println("Encrypted JSON (Base64): " + java.util.Base64.getEncoder().encodeToString(encryptedData));
        System.out.println("Decrypted JSON: " + decryptedData);
    }

    public static byte[] encrypt(String data, PGPPublicKey publicKey) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Create an encrypted data generator
        PGPEncryptedDataGenerator encGen = new PGPEncryptedDataGenerator(
                new JcePGPDataEncryptorBuilder(SymmetricKeyAlgorithmTags.AES_256)
                        .setWithIntegrityPacket(true)
                        .setSecureRandom(new SecureRandom())
                        .setProvider("BC"));

        // Add encryption method (public key encryption)
        encGen.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(publicKey).setProvider("BC"));

        OutputStream encryptedOut = encGen.open(outputStream, new byte[4096]);
        encryptedOut.write(data.getBytes());
        encryptedOut.close();

        return outputStream.toByteArray();
    }

    public static String decrypt(byte[] encryptedData, PGPPrivateKey privateKey) throws Exception {
        InputStream in = new ByteArrayInputStream(encryptedData);
        PGPObjectFactory pgpFactory = new PGPObjectFactory(PGPUtil.getDecoderStream(in), new JcaKeyFingerprintCalculator());
        Object pgpObject = pgpFactory.nextObject();

        if (!(pgpObject instanceof PGPEncryptedDataList)) {
            pgpObject = pgpFactory.nextObject();
        }

        PGPEncryptedDataList encList = (PGPEncryptedDataList) pgpObject;
        PGPPublicKeyEncryptedData encData = (PGPPublicKeyEncryptedData) encList.get(0);

        // Decrypt the data using the private key
        InputStream clearData = encData.getDataStream(
                new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC").build(privateKey));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int ch;
        while ((ch = clearData.read()) >= 0) {
            out.write(ch);
        }
        return out.toString();
    }

    public static PGPKeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return new JcaPGPKeyPair(PGPPublicKey.RSA_GENERAL, keyPairGenerator.generateKeyPair(), new java.util.Date());
    }
}
