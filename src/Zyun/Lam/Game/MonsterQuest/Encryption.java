package Zyun.Lam.Game.MonsterQuest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import javax.crypto.Cipher;


class SHA256 {
    public static byte [] hash (String text){
        byte[] hash = null;
        byte[] hashing = {10, 20, 34};
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Unable to hash! : " + ex.getMessage());
        }
        if (hash == null)
            hash = hashing;
        return hash;
    }
}
class RSA {
      public static byte[] encrypt(String text, PublicKey key) {
        byte[] cipherText = null;
        try {
          // get an RSA cipher object and print the provider
          final Cipher cipher = Cipher.getInstance("RSA");
          // encrypt the plain text using the public key
          cipher.init(Cipher.ENCRYPT_MODE, key);
          cipherText = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
          e.printStackTrace();
        }
        return cipherText;
    }
}