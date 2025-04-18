package com.fly.main;

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

    }

    class run extends Thread {
        @Override
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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

        g.drawImage(buffimg,0,0,null);

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
