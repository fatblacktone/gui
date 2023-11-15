package dnvms.encryption;



import javax.crypto.Cipher;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.InvalidKeyException;
 
 
public class PasswordEncrypter {
 
    private static String algorithm = "DESede";
    private static Key key = null;
    private static Cipher cipher = null;
    private static PasswordEncrypter obj = new PasswordEncrypter();
 
    private PasswordEncrypter() {
        try {
            key = KeyGenerator.getInstance(algorithm).generateKey();
            cipher = Cipher.getInstance(algorithm);
        } catch (Exception e) {
        }
    }
 
    public static PasswordEncrypter getInstance() {
        return obj;
    }
 
    public static byte[] encrypt(String input)
            throws InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] inputBytes = input.getBytes();
        return cipher.doFinal(inputBytes);
    }
 
    public static String getEncryptStringValue(String input) throws InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        return new String(encrypt(input));
    }
 
 
    public static String decrypt(byte[] encryptionBytes)
            throws InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] recoveredBytes =
                cipher.doFinal(encryptionBytes);
        String recovered =
                new String(recoveredBytes);
        return recovered;
    }
}