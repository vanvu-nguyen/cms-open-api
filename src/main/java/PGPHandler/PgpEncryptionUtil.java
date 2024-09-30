package PGPHandler;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Objects;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;

public class PgpEncryptionUtil {
    private int compressionAlgorithm;
    private int symmetricKeyAlgorithm;
    private boolean armor;
    private boolean withIntegrityCheck;
    private int bufferSize;

    public void encrypt(OutputStream encryptOut, InputStream clearIn, long length, InputStream publicKeyIn) throws IOException, PGPException {
        PGPCompressedDataGenerator compressedDataGenerator = new PGPCompressedDataGenerator(this.compressionAlgorithm);
        PGPEncryptedDataGenerator pgpEncryptedDataGenerator = new PGPEncryptedDataGenerator((new JcePGPDataEncryptorBuilder(this.symmetricKeyAlgorithm)).setWithIntegrityPacket(this.withIntegrityCheck).setSecureRandom(new SecureRandom()).setProvider("BC"));
        pgpEncryptedDataGenerator.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(CommonOpenPGPUtils.getPublicKey(publicKeyIn)));
        if (this.armor) {
            encryptOut = new ArmoredOutputStream((OutputStream)encryptOut);
        }

        OutputStream cipherOutStream = pgpEncryptedDataGenerator.open((OutputStream)encryptOut, new byte[this.bufferSize]);
        CommonOpenPGPUtils.copyAsLiteralData(compressedDataGenerator.open(cipherOutStream), clearIn, length, this.bufferSize);
        compressedDataGenerator.close();
        cipherOutStream.close();
        ((OutputStream)encryptOut).close();
    }

    public byte[] encrypt(byte[] clearData, InputStream pubicKeyIn) throws PGPException, IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(clearData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        this.encrypt(outputStream, inputStream, (long)clearData.length, pubicKeyIn);
        return outputStream.toByteArray();
    }

    public InputStream encrypt(InputStream clearIn, long length, InputStream publicKeyIn) throws IOException, PGPException {
        File tempFile = File.createTempFile("pgp-", "-encrypted");
        this.encrypt(Files.newOutputStream(tempFile.toPath()), clearIn, length, publicKeyIn);
        return Files.newInputStream(tempFile.toPath());
    }

    public byte[] encrypt(byte[] clearData, String publicKeyStr) throws PGPException, IOException {
        return this.encrypt(clearData, IOUtils.toInputStream(publicKeyStr, StandardCharsets.UTF_8));
    }

    public InputStream encrypt(InputStream clearIn, long length, String publicKeyStr) throws IOException, PGPException {
        return this.encrypt(clearIn, length, IOUtils.toInputStream(publicKeyStr, StandardCharsets.UTF_8));
    }

    private static int $default$compressionAlgorithm() {
        return 1;
    }

    private static int $default$symmetricKeyAlgorithm() {
        return 7;
    }

    private static boolean $default$armor() {
        return true;
    }

    private static boolean $default$withIntegrityCheck() {
        return true;
    }

    private static int $default$bufferSize() {
        return 65536;
    }

    public static PgpEncryptionUtilBuilder builder() {
        return new PgpEncryptionUtilBuilder();
    }

    public int getCompressionAlgorithm() {
        return this.compressionAlgorithm;
    }

    public int getSymmetricKeyAlgorithm() {
        return this.symmetricKeyAlgorithm;
    }

    public boolean isArmor() {
        return this.armor;
    }

    public boolean isWithIntegrityCheck() {
        return this.withIntegrityCheck;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public PgpEncryptionUtil(final int compressionAlgorithm, final int symmetricKeyAlgorithm, final boolean armor, final boolean withIntegrityCheck, final int bufferSize) {
        this.compressionAlgorithm = compressionAlgorithm;
        this.symmetricKeyAlgorithm = symmetricKeyAlgorithm;
        this.armor = armor;
        this.withIntegrityCheck = withIntegrityCheck;
        this.bufferSize = bufferSize;
    }

    static {
        if (Objects.isNull(Security.getProvider("BC"))) {
            Security.addProvider(new BouncyCastleProvider());
        }

    }

    public static class PgpEncryptionUtilBuilder {
        private boolean compressionAlgorithm$set;
        private int compressionAlgorithm$value;
        private boolean symmetricKeyAlgorithm$set;
        private int symmetricKeyAlgorithm$value;
        private boolean armor$set;
        private boolean armor$value;
        private boolean withIntegrityCheck$set;
        private boolean withIntegrityCheck$value;
        private boolean bufferSize$set;
        private int bufferSize$value;

        PgpEncryptionUtilBuilder() {
        }

        public PgpEncryptionUtilBuilder compressionAlgorithm(final int compressionAlgorithm) {
            this.compressionAlgorithm$value = compressionAlgorithm;
            this.compressionAlgorithm$set = true;
            return this;
        }

        public PgpEncryptionUtilBuilder symmetricKeyAlgorithm(final int symmetricKeyAlgorithm) {
            this.symmetricKeyAlgorithm$value = symmetricKeyAlgorithm;
            this.symmetricKeyAlgorithm$set = true;
            return this;
        }

        public PgpEncryptionUtilBuilder armor(final boolean armor) {
            this.armor$value = armor;
            this.armor$set = true;
            return this;
        }

        public PgpEncryptionUtilBuilder withIntegrityCheck(final boolean withIntegrityCheck) {
            this.withIntegrityCheck$value = withIntegrityCheck;
            this.withIntegrityCheck$set = true;
            return this;
        }

        public PgpEncryptionUtilBuilder bufferSize(final int bufferSize) {
            this.bufferSize$value = bufferSize;
            this.bufferSize$set = true;
            return this;
        }

        public PgpEncryptionUtil build() {
            int compressionAlgorithm$value = this.compressionAlgorithm$value;
            if (!this.compressionAlgorithm$set) {
                compressionAlgorithm$value = PgpEncryptionUtil.$default$compressionAlgorithm();
            }

            int symmetricKeyAlgorithm$value = this.symmetricKeyAlgorithm$value;
            if (!this.symmetricKeyAlgorithm$set) {
                symmetricKeyAlgorithm$value = PgpEncryptionUtil.$default$symmetricKeyAlgorithm();
            }

            boolean armor$value = this.armor$value;
            if (!this.armor$set) {
                armor$value = PgpEncryptionUtil.$default$armor();
            }

            boolean withIntegrityCheck$value = this.withIntegrityCheck$value;
            if (!this.withIntegrityCheck$set) {
                withIntegrityCheck$value = PgpEncryptionUtil.$default$withIntegrityCheck();
            }

            int bufferSize$value = this.bufferSize$value;
            if (!this.bufferSize$set) {
                bufferSize$value = PgpEncryptionUtil.$default$bufferSize();
            }

            return new PgpEncryptionUtil(compressionAlgorithm$value, symmetricKeyAlgorithm$value, armor$value, withIntegrityCheck$value, bufferSize$value);
        }

        public String toString() {
            return "PgpEncryptionUtil.PgpEncryptionUtilBuilder(compressionAlgorithm$value=" + this.compressionAlgorithm$value + ", symmetricKeyAlgorithm$value=" + this.symmetricKeyAlgorithm$value + ", armor$value=" + this.armor$value + ", withIntegrityCheck$value=" + this.withIntegrityCheck$value + ", bufferSize$value=" + this.bufferSize$value + ")";
        }
    }
}

