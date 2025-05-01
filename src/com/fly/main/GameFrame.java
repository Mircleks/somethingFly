package com.fly.main;

import com.fly.util.RecordManager;

import javax.swing.*;

import static com.fly.util.Constant.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class GameFrame extends Frame {

    //initialize gamebackGround
    private GameBackGround gameBackGround;

    private Flier flier;

    private volatile boolean gameRunning = true;


    private GameBarrierLayer gameBarrierLayer;

    private GameFrontGround gameFrontGround;

    private BufferedImage buffimg = new BufferedImage(FRAM_WIDTH,FRAM_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);


    public GameFrame() {
        setVisible(true);
        setSize(FRAM_WIDTH, FRAM_HEIGHT);

        setTitle(FRAM_TITLE);

        setLocation(FRAM_X, FRAM_Y);

        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);//finish the program
            }
        });

        initGamg();

        new run().start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                add(e);

            }

            @Override
            public void keyReleased(KeyEvent e) {
                minu(e);

            }
        });




    }

    public void initGamg() {
        gameBackGround = new GameBackGround();
        flier = new Flier();
        gameFrontGround = new GameFrontGround();

        gameBarrierLayer = new GameBarrierLayer();

    }

    class run extends Thread {
        @Override
        public void run() {
            while(gameRunning){
                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Override
    public void update(Graphics g) {

        Graphics graphics = buffimg.getGraphics();



        gameBackGround.draw(graphics);
        flier.draw(graphics);
        gameFrontGround.draw(graphics);
        gameBarrierLayer.logic();
        gameBarrierLayer.draw(graphics);
        gameBarrierLayer.drawTimer(graphics);

        //check collision
        checkCollision();

        g.drawImage(buffimg,0,0,null);

    }


    // check collision
    private void checkCollision() {
        if (!gameRunning) return;
        Rectangle flierRect = flier.getRect();
        for (Barrier barrier : gameBarrierLayer.getBarriers()) {
            if (flierRect.intersects(barrier.getRect())) {
                handleCollision(); // 触发碰撞处理
                return;
            }
        }
    }

    private void handleCollision() {
        gameRunning = false; // 停止游戏循环
        System.out.println("Game Over!");


        long currentTime = gameBarrierLayer.getCurrentTime();

        RecordManager.updateRecord(currentTime);

        // Force refresh the interface to ensure that the screen remains at the moment of collision
        repaint();

        SwingUtilities.invokeLater(() -> {
            new GameOverDiaLog(this).setVisible(true); // 显示弹窗
        });
    }

    public void restartGame() {
        // 重置游戏状态
        initGamg(); // 重新初始化游戏元素
        gameRunning = true;
        new run().start(); // 重启游戏线程
    }





    public void add(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                flier.fly(1);
                break;
        }

    }
    public void minu(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                flier.fly(5);
                break;
        }

    }
}
