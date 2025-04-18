package com.fly.main;

import com.fly.util.Constant;
import com.fly.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameBackGround {
   /* private BufferedImage bkimg;


    public GameBackGround() {
        bkimg = GameUtil.loadBufferedImage(Constant.BK_IMG_OATH);
    }

    //Draw the background image
    public void draw(Graphics g) {
        int height = bkimg.getHeight();
        int wei = bkimg.getWidth();

        //loop times
        int count = Constant.FRAM_WIDTH/wei + 1;
        for (int i = 0; i < count; i++) {
            g.drawImage(bkimg, i * wei, Constant.FRAM_HEIGHT-height, null);
        }



    }*/
   private BufferedImage backgroundImage;
    private int x1, x2;
    private static final int SPEED = 2;
    private static final int OVERLAP = 1;

    public GameBackGround() {
        backgroundImage = GameUtil.loadBufferedImage(Constant.BK_IMG_OATH);
        x1 = 0;
        x2 = backgroundImage.getWidth() - OVERLAP;
    }

    public void draw(Graphics g) {

        g.drawImage(backgroundImage, x1, 0, null);
        g.drawImage(backgroundImage, x2, 0, null);


        x1 -= SPEED;
        x2 -= SPEED;


        if (x1 + backgroundImage.getWidth() <= 0) {
            x1 = x2 + backgroundImage.getWidth() - OVERLAP;
        }
        if (x2 + backgroundImage.getWidth() <= 0) {
            x2 = x1 + backgroundImage.getWidth() - OVERLAP;
        }
    }
}
