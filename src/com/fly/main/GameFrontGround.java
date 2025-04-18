package com.fly.main;

import com.fly.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameFrontGround {
    private static final int CLOUD_COUNT = 2;
    private List<Cloud> clouds;
    private static final int CLOUD_SPEED = 2;
    private BufferedImage[] img;

    private Random random;



    public GameFrontGround(){
        clouds = new ArrayList<>();
        img = new BufferedImage[CLOUD_COUNT];


        for(int i = 0; i < CLOUD_COUNT; i++){
            img[i] = GameUtil.loadBufferedImage("image/clouds_"+i+".png");


        }
        random = new Random();



    }

    public void draw(Graphics g){
        /*Cloud cloud = new Cloud(img[1],CLOUD_SPEED,400,50);
        clouds.add(cloud);*/

        logic();

        for(int i = 0; i < clouds.size(); i++){
            clouds.get(i).draw(g);
        }


    }

    private void logic(){
        if((int)(500*Math.random())<1){
            Cloud cloud = new Cloud(img[random.nextInt(CLOUD_COUNT)],CLOUD_SPEED,1920,random.nextInt(500));
            clouds.add(cloud);
        }
        for(int i = 0; i<clouds.size(); i++){
            Cloud cloud = clouds.get(i);
            if(cloud.isOutFrame()){
                clouds.remove(cloud);
                i--;
                System.out.println("remove cloud"+cloud);
            }


        }


    }

}
