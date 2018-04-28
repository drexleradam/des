package hu.pte.ttk.des;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MyDes {

    private Cipher desCipher;

    public MyDes() {
        try {
            desCipher = Cipher.getInstance("DES/ECB/NoPadding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public byte[] encrypt(byte[] text, String key) {
        byte[] output = null;
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "DES");

            desCipher.init(Cipher.ENCRYPT_MODE, keySpec);

            output = desCipher.doFinal(text);

        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return output;
    }

    public byte[] decrypt(byte[] text, String key) {
        byte[] output = null;
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "DES");

            desCipher.init(Cipher.DECRYPT_MODE, keySpec);

            output = desCipher.doFinal(text);

        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return output;
    }
}
