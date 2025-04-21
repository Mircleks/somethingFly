// RecordManager.java
package com.fly.util;

import java.io.*;

public class RecordManager {
    private static final String RECORD_PATH = "record.txt";
    private static long highestTime = 0;

    static {
        loadRecord();
    }

    // Load the highest record from the file
    private static void loadRecord() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RECORD_PATH))) {
            String line = reader.readLine();
            if (line != null) {
                highestTime = Long.parseLong(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            // 文件不存在或格式错误时初始化
            highestTime = 0;
            saveRecord();
        }
    }

    // Save the highest record to the file
    public static void saveRecord() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORD_PATH))) {
            writer.write(String.valueOf(highestTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update the highest record (only when the current time is higher)
    public static void updateRecord(long currentTime) {
        if (currentTime > highestTime) {
            highestTime = currentTime;
            saveRecord();
        }
    }

    public static long getHighestTime() {
        return highestTime;
    }
}