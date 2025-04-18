package com.fly.main;

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
    public void draw(Graphics g){
        Barrier barrier= new Barrier(800,20,450,0);
        //barrier.draw(g);
        barriers.add(barrier);
        barriers.get(0).draw(g);
        //从下往上障碍物的y是由整个界面的高度减去输入的height决定的
        Barrier barrier1 = new Barrier(900,0,450,2);
        barriers.add(barrier1);
        barriers.get(1).draw(g);
        //logic();
    }

    public void logic(){
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
    }

    private int numberTop;
    private int numberDown;

    public void ran(){
        numberTop = random.nextInt(20);
        numberDown = random.nextInt(450)+550;


    }
}
