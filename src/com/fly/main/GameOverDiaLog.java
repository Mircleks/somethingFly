// GameOverDialog.java
package com.fly.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverDiaLog extends JDialog {
    private final GameFrame gameFrame;

    public GameOverDiaLog(GameFrame owner) {
        super(owner, "Game Over", true);
        this.gameFrame = owner;

        // 设置弹窗布局
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 游戏结束标签
        JLabel label = new JLabel("Game Over！", SwingConstants.CENTER);
        label.setFont(new Font("宋体", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);

        // 重新开始按钮
        JButton restartButton = new JButton("Restart Game");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.restartGame();
                // Close the pop-up window
                dispose();
            }
        });
        panel.add(restartButton, BorderLayout.SOUTH);

        // Set the pop-up window properties
        getContentPane().add(panel);
        setSize(300, 150);
        setLocationRelativeTo(owner);
        setResizable(false);

    }
}