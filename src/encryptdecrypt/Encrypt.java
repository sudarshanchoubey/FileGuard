/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptdecrypt;

import static encryptdecrypt.EncryptDecrypt.getFile;
import static encryptdecrypt.EncryptDecrypt.saveFile;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author schoubey
 */
public class Encrypt {
    private final String filePath;
    private final String passKey;
    public Encrypt(String filePath, String passkey) {
        this.filePath = filePath;
        this.passKey = passkey;
    }
    
    public byte[] encryptFile(Key key, byte[] content) {
        Cipher cipher;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;
    }
    public void encryptFile() throws UnsupportedEncodingException, NoSuchAlgorithmException, IOException {
        SecretKeySpec secretKey;
        byte[] key;
        String myKey = "ThisIsAStrongPasswordForEncryptionAndDecryption";

        MessageDigest sha = null;
        key = passKey.getBytes("UTF-8");
        System.out.println(key.length);
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // use only first 128 bit
        System.out.println(key.length);
        System.out.println(new String(key, "UTF-8"));
        secretKey = new SecretKeySpec(key, "AES");

        
        System.out.println(secretKey);
        byte[] content = getFile(filePath);
        byte[] encrypted = encryptFile(secretKey, content);
        System.out.println("Encryption done");
        saveFile(encrypted, filePath + ".enc");
        System.out.println("Encrypted file written");
        System.out.println("Done");
    }
}
