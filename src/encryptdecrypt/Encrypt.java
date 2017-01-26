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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author schoubey
 */
public class Encrypt {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        SecretKeySpec secretKey;
        byte[] key;
        String myKey = "ThisIsAStrongPasswordForEncryptionAndDecryption";

        MessageDigest sha = null;
        key = myKey.getBytes("UTF-8");
        System.out.println(key.length);
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // use only first 128 bit
        System.out.println(key.length);
        System.out.println(new String(key, "UTF-8"));
        secretKey = new SecretKeySpec(key, "AES");

        
        System.out.println(secretKey);
        byte[] content = getFile("C:\\Users\\schoubey\\Desktop\\sunsout.jpg");
        byte[] encrypted = encryptFile(secretKey, content);
        System.out.println("Encryption done");
        saveFile(encrypted, "C:\\Users\\schoubey\\Desktop\\sunsout.enc");
        System.out.println("Encrypted file written");
        System.out.println("Done");
    }
}
