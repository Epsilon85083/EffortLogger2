//Author: Joseph Felix
package application;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

//Encryption Class
public class EncryptionUtil {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String CHARSET = "UTF-8";

    //method for encrypting and returning a log string
    public static String encrypt(String key, String value) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(key));
            byte[] encrypted = cipher.doFinal(value.getBytes(CHARSET));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //method for decrypting an encrypted log string and returning it
    public static String decrypt(String key, String encrypted) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(key));
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original, CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //method for generating key for encryption and decryption
    private static Key generateKey(String key) throws Exception {
        byte[] keyBytes = key.getBytes(CHARSET);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }
    
}