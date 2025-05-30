package com.fly.main;

import com.fly.util.Constant;
import com.fly.util.RecordManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBarrierLayer {


    private GameTime gameTime;

    // Whether to display prompt information
    private boolean showInstructions = true;

    // The time when the prompt information begins to be displayed
    private long instructionStartTime;

    private Random random = new Random();

    private List<Barrier> barriers;

    public GameBarrierLayer(){
        gameTime = new GameTime();
        barriers = new ArrayList<>();

        // Record the start time of the prompt message
        instructionStartTime = System.currentTimeMillis();

    }


    public void draw(Graphics g) {
        for (Barrier barrier : barriers) {
            // Automatically draw all obstacles
            barrier.draw(g);
        }

        // Draw prompt information
        if (showInstructions) {
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.setColor(Color.RED);
            g.drawString("Press the up key to rise and avoid obstacles!", 600, 1000);

            // Check if it exceeds 5 seconds and hide the prompt message
            if (System.currentTimeMillis() - instructionStartTime > 5000) {
                showInstructions = false;
            }
        }
    }


    public void logic() {
        if (barriers.isEmpty()) {
            ran();

            gameTime.begin();
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


    public void drawTimer(Graphics g) {
        if (gameTime != null) {
            long differ = gameTime.differ();
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            g.setColor(Color.WHITE);
            g.drawString("Current: " + differ + "s | Best: " + RecordManager.getHighestTime() + "s", 100, 100);
        }
    }


    public long getCurrentTime(){
        return gameTime.differ();
    }

    private int numberTop;
    private int numberDown;

    private void ran() {


            numberTop = random.nextInt(200)+1100 ;
            numberDown = random.nextInt(400) + 150;

    }

    public List<Barrier> getBarriers() {
        return barriers;
    }
}
