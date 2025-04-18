package com.fly.main;

import com.fly.util.Constant;
import com.fly.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Barrier {
    private static BufferedImage[] imgs;

    private int speed = 3;

    static{
        final int COUNT = 3;

        imgs = new BufferedImage[COUNT];

        for (int i = 0; i < COUNT; i++) {
            imgs[i] = GameUtil.loadBufferedImage(Constant.BARRIER_IMG_PATH[i]);
        }
    }

    private int x,y;
    private int width,height;
    private int type;

    public static final int TYPE_TOP_NORMAL = 0;
    public static final int TYPE_BOTTOM_NORMAL = 2;
    public static final int TYPE_HOVER_NORMAL = 4;

    public static final int BARRIER_WIDTH = imgs[0].getWidth();
    public static final int BARRIER_HEIGHT = imgs[0].getHeight();
    public static final int BARRIER_HEAD_WIDTH = imgs[1].getWidth();
    public static final int BARRIER_HEAD_HEIGHT = imgs[1].getHeight();

    public Barrier(){}

    public Barrier(int x, int y,  int height, int type) {
        this.x = x;
        this.y = y;
        this.width = BARRIER_WIDTH;
        this.height = height;
        this.type = type;
    }

    //according type draw
    public void draw(Graphics g){
        switch(type){
            case TYPE_TOP_NORMAL:
                drawTopNormal(g);
                break;
            case TYPE_BOTTOM_NORMAL:
                drawNomalTop(g);
                break;
        }
    }

    private void drawTopNormal(Graphics g){
        int count = (height - BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT+1;
        //绘制第一个障碍物
        /*for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0],x,y+i*BARRIER_HEIGHT,null);

        }*/

        //此处绘制第二个障碍物
        //int y = height-BARRIER_HEAD_HEIGHT;
        g.drawImage(imgs[2],x-(BARRIER_HEAD_WIDTH)/2,y,null);
        x-= speed;

    }

    private void drawNomalTop(Graphics g){

        int count = height/BARRIER_HEIGHT+1;
        int y = Constant.FRAM_HEIGHT-height;
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[1],x-(BARRIER_HEAD_WIDTH-BARRIER_WIDTH)/2,y,null);
        }
        x -= speed;


    }
    //判断绘制下一组障碍物的时机
    public boolean isInFrame(){
        return 1920-x>150;
    }

}
