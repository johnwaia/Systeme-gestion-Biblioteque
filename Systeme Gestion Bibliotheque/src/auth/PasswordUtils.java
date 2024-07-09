package auth;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordUtils {
    private static final String SECRET_KEY = "your-secret-key2"; // 16 caractère
    private static final String SALT = "your-salt"; // replace with your own salt
    private static final byte[] IV = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F };

    public static String encrypt(String password) {
        try {
            IvParameterSpec iv = new IvParameterSpec(IV);
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            byte[] encrypted = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encryptedPassword) {
        try {
            IvParameterSpec iv = new IvParameterSpec(IV);
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(original);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
