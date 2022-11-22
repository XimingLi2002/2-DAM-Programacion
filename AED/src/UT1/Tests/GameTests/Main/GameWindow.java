package UT1.Tests.GameTests.Main;

import javax.swing.*;

public class GameWindow extends JFrame{
    GameWindow(GamePanel gamePanel){
        this.add(gamePanel);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
