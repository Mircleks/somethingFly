package com.fly.main;

import com.fly.util.Constant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBarrierLayer {

    private Random random = new Random();

    private List<Barrier> barriers;

    public GameBarrierLayer(){
        barriers = new ArrayList<>();

    }
    /*public void draw(Graphics g){
        Barrier barrier= new Barrier(800,20,450,0);
        //barrier.draw(g);
        barriers.add(barrier);
        barriers.get(0).draw(g);
        //从下往上障碍物的y是由整个界面的高度减去输入的height决定的
        Barrier barrier1 = new Barrier(900,0,450,2);
        barriers.add(barrier1);
        barriers.get(1).draw(g);
        //logic();
    }*/
    public void draw(Graphics g) {
        for (Barrier barrier : barriers) {
            // Automatically draw all obstacles
            barrier.draw(g);
        }
    }

    /*public void logic(){
        if(barriers.size()==0){
            ran();
            Barrier top = new Barrier(800,numberTop,numberTop,0);
            barriers.add(top);
            Barrier down = new Barrier(800,1000-numberDown,1000-numberTop,2);
            barriers.add(down);


        }
        else{
            Barrier last = barriers.get(barriers.size()-1);
            if (last.isInFrame()){
                ran();
                Barrier top = new Barrier(800,numberTop,numberTop,0);
                barriers.add(top);
                Barrier down = new Barrier(800,1000-numberDown,1000-numberTop,2);
                barriers.add(down);
            }

        }
    }*/
    public void logic() {
        if (barriers.isEmpty()) {
            ran();

            // Generate the top obstacle(The height is numberTop, with y starting from the top)
            Barrier top = new Barrier(1600, Constant.FRAM_HEIGHT-numberTop, numberTop, Barrier.TYPE_TOP_NORMAL);
            barriers.add(top);
            // 生成底部障碍物（高度为 numberDown，y 从底部计算）
            Barrier down = new Barrier(1600, Constant.FRAM_HEIGHT - numberDown, numberDown, Barrier.TYPE_BOTTOM_NORMAL);
            barriers.add(down);
        } else {
            Barrier last = barriers.get(barriers.size() - 1);
            if (last.isInFrame()) { // A new one is generated when the last obstacle enters the screen
                ran();
                // The x-coordinate of the new obstacle is based on the position of the last obstacle
                Barrier top = new Barrier(last.getX() + 400, 0, numberTop, Barrier.TYPE_TOP_NORMAL);
                barriers.add(top);
                Barrier down = new Barrier(last.getX() + 400, Constant.FRAM_HEIGHT - numberDown, numberDown, Barrier.TYPE_BOTTOM_NORMAL);
                barriers.add(down);
            }
        }
        // Remove the obstacles that have moved out of the screen
        barriers.removeIf(barrier -> barrier.getX() + Barrier.BARRIER_WIDTH < 0);
    }

    private int numberTop;
    private int numberDown;

    private void ran() {
        // 确保障碍物之间有足够空隙（例如至少 200 像素）


            numberTop = random.nextInt(200)+1100 ;
            numberDown = random.nextInt(400) + 150;

    }

    public List<Barrier> getBarriers() {
        return barriers;
    }
}
