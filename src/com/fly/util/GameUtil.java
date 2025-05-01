package com.fly.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class GameUtil {
    public static BufferedImage loadBufferedImage(String imagePath) {
        try {
            return ImageIO.read(new FileInputStream(imagePath));
        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return null;

    }
}
