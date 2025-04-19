package com.fly.main;

import com.fly.util.Constant;
import com.fly.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Barrier {
    private static BufferedImage[] imgs;

    private Rectangle rect;

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
    public static final int barrier_down_width = imgs[2].getWidth();
    public static final int barrier_down_height = imgs[2].getHeight();

    public Barrier(){


    }

    public Barrier(int x, int y,  int height, int type) {
        this.x = x;
        this.y = y;
        this.width = BARRIER_WIDTH;
        this.height = height;
        this.type = type;


        // 初始化碰撞矩形
        if (type == TYPE_TOP_NORMAL) {
            // 顶部障碍物的矩形（覆盖整个障碍物）
            rect = new Rectangle(
                    x , // 根据实际绘制位置调整
                    Constant.FRAM_HEIGHT - height, // y 坐标从屏幕底部向上计算
                    barrier_down_width-30, // 使用头部宽度（可能更宽）
                    barrier_down_height-30// 高度为障碍物高度
            );
        } else if (type == TYPE_BOTTOM_NORMAL) {
            // 底部障碍物的矩形
            rect = new Rectangle(
                    x, // 根据绘制偏移调整
                    y, // y 坐标为传入的底部位置
                    BARRIER_HEAD_WIDTH-40,
                    height
            );
        }
    }

    //according type draw
    public void draw(Graphics g){
        switch(type){
            case TYPE_TOP_NORMAL:
                drawTopNormal(g);
                break;
            case TYPE_BOTTOM_NORMAL:
                drawBottomNormal(g);
                break;
        }
        g.setColor(Color.RED);
        g.drawRect(rect.x, rect.y, rect.width, rect.height); // 绘制矩形
    }

    private void drawTopNormal(Graphics g){
        int count = (height - BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT+1;

        /*for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0],x,y+i*BARRIER_HEIGHT,null);

        }*/


        //int y = height-BARRIER_HEAD_HEIGHT;
        int barrierBottomY = Constant.FRAM_HEIGHT - height;
        g.drawImage(imgs[2],x-(BARRIER_HEAD_WIDTH)/2,barrierBottomY,null);
        x-= speed;
        rect.x = x - BARRIER_HEAD_WIDTH / 2; // 更新矩形位置

    }



    public int getX() {
        return x;
    }

    private void drawBottomNormal(Graphics g){

        int count = height/BARRIER_HEIGHT+1;
        int y = Constant.FRAM_HEIGHT-height;
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[1],x-(BARRIER_HEAD_WIDTH-BARRIER_WIDTH)/2,y,null);
        }
        x -= speed;
        rect.x = x - (BARRIER_HEAD_WIDTH - BARRIER_WIDTH) / 2;


    }
    // Determine the timing for drawing the next group of obstacles
    /*public boolean isInFrame(){
        return 1920-x>150;
    }*/
    public boolean isInFrame() {
        //A new obstacle is generated when the right side of the obstacle enters 150 pixels on the right side of the screen
        return (x + BARRIER_WIDTH) > (1920 - 150);
    }

    public Rectangle getRect() {
        return rect;
    }

}
