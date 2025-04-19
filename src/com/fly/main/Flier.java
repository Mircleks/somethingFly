package com.fly.main;

import com.fly.util.GameUtil;

import static com.fly.util.Constant.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Flier {
    private BufferedImage[] images;
    public static final int IMG_COUNT = 3;

    private int state;
    public static final int state_NORMAL = 0;
    public static final int state_UP = 1;
    public static final int state_DOWN = 2;

    private Rectangle rect;

    private int x =200, y=200;

    public Rectangle getRect() {
        return rect;
    }

    private  boolean up=false,down=false;
    private int speed=6;


    public Flier(){
        images = new BufferedImage[IMG_COUNT];
        for (int i = 0; i < IMG_COUNT; i++) {
            images[i] = GameUtil.loadBufferedImage(Flier_IMG[i]);
        }

       /* int w = images[0].getWidth()-110;
        int h = images[0].getHeight()-50;*/
        int w = 20;
        int h = 20;
        rect = new Rectangle(w,h);


    }
    public void draw(Graphics g){
        flyLogic();
        g.drawImage(images[state],x,y,null);

        g.drawRect(x+90,y+60,(int)rect.getWidth(),rect.height);
        rect.x=this.x;
        rect.y=this.y;
    }

    public void flyLogic(){
        if(up){
            y-=speed;
            if (y<20){
                y=20;
            }
        }
        if(!up){
            y+=speed;
            if(y>1000){
                y=980;
            }
        }
    }

    //control flier move
    public void fly(int fly){
        switch (fly){
            case 1:
                state=1;
                up=true;
                break;
            case 5:
                state=2;
                up=false;
                break;


        }
    }


}
