package com.fly.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class RecordManager {
    private static final String RECORD_PATH = "record.txt";
    private static final String SECRET_KEY = "1234567890123456"; // 16-byte key for AES
    private static long highestTime = 0;

    static {
        loadRecord();
    }

    // Load the record and decrypt
    private static void loadRecord() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RECORD_PATH))) {
            String line = reader.readLine();
            if (line != null) {
                String decrypted = decrypt(line.trim(), SECRET_KEY);
                highestTime = Long.parseLong(decrypted);
            }
        } catch (IOException | NumberFormatException e) {
            highestTime = 0;
            saveRecord();
        }
    }

    //Save the records and encrypt them
    public static void saveRecord() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORD_PATH))) {
            String encrypted = encrypt(String.valueOf(highestTime), SECRET_KEY);
            writer.write(encrypted);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update the highest record
    public static void updateRecord(long currentTime) {
        if (currentTime > highestTime) {
            highestTime = currentTime;
            saveRecord();
        }
    }

    public static long getHighestTime() {
        return highestTime;
    }

    // AES encryption
    private static String encrypt(String data, String key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting", e);
        }
    }


    // AES Decryption
    private static String decrypt(String data, String key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(data);
            return new String(cipher.doFinal(decodedBytes));
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting", e);
        }
    }
}