/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptdecrypt;

import static encryptdecrypt.EncryptDecrypt.decryptFile;
import static encryptdecrypt.EncryptDecrypt.encryptFile;
import static encryptdecrypt.EncryptDecrypt.getFile;
import static encryptdecrypt.EncryptDecrypt.saveFile;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author schoubey
 */
public class Decrypt {
    private final String filePath;
    private final String passKey;
    
    public Decrypt(String filePath, String passkey) {
        this.filePath = filePath;
        this.passKey = passkey;
    }
    
    public void decrypt() throws UnsupportedEncodingException, NoSuchAlgorithmException, IOException {
        SecretKeySpec secretKey;
        byte[] key;
        
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
        byte[] encContent = getFile(filePath);
        byte[] decrypted = decryptFile(secretKey, encContent);
        
        saveFile(decrypted, filePath + ".dec");
        System.out.println("Done");
    }
}
