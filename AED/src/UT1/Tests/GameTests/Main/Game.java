package UT1.Tests.GameTests.Main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 240;
    private final int UPS_SET = 200;

    Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        gamePanel.updateGame();
    }

    @Override
    public void run() {
        //Hay que trabajar en nanosegundos 1s = 1 000 000 000ns
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = 0;

        double deltaUpdate = 0;
        double deltaFrame = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaUpdate += (currentTime - previousTime) / timePerUpdate;
            deltaFrame += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaUpdate >= 1) {
                update();
                updates++;
                deltaUpdate--;
            }

            if (deltaFrame >= 1) {
                gamePanel.repaint();
                frames++;
                deltaFrame--;
            }

            //Muestra FPS y UPS por segundo
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println(frames + "   " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
