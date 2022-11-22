package UT1.Tests.GameTests.Main;

import UT1.Tests.GameTests.Inputs.KeyboardInputs;
import UT1.Tests.GameTests.Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static UT1.Tests.GameTests.Utilz.Constants.PlayerConstans.*;
import static UT1.Tests.GameTests.Utilz.Constants.Directions.*;

public class GamePanel extends JPanel {
    protected MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage image;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 40;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;
    private int speed = 2;
    GamePanel(){
        mouseInputs = new MouseInputs(this);
        setPanelSize();
        importImage();
        loadAnimations();
        this.addKeyListener(new KeyboardInputs(this));
//        this.addMouseListener(mouseInputs);
//        this.addMouseMotionListener(mouseInputs);
        this.setBackground(Color.BLACK);
    }
    public void setDirection(int direction){
        this.playerDirection = direction;
        moving = true;
    }
    public void setMoving(boolean moving){
        this.moving = moving;
    }
    private void setAnimation(){
        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }
    private void updatePos() {
        if (moving) {
            switch (playerDirection) {
                case LEFT:
                    xDelta -= speed;
                    break;
                case UP:
                    yDelta -= speed;
                    break;
                case RIGHT:
                    xDelta += speed;
                    break;
                case DOWN:
                    yDelta += speed;
                    break;
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(animations[playerAction][animationIndex], (int) xDelta, (int) yDelta,128,80,null);

    }


    private void setPanelSize(){
        //Las imagenes son de 32 pixeles por eso pillamos 800 de altura 800/32=25
        //De ancho serán 1280/32=40
        Dimension size = new Dimension(1290,800);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setPreferredSize(size);
    }
    private void importImage(){
        InputStream inputStream = getClass().getResourceAsStream("../Resources/player_sprites.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            System.out.println("Not found image");
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void loadAnimations(){
        //fila 1 de la imagen
        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[i].length; i++) {
                animations[j][i] = image.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }
    private void UpdateAnimationTick(){
        animationTick++;
        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(playerAction)){
                animationIndex = 0;
            }
        }
    }

    public void updateGame(){
        UpdateAnimationTick();
        setAnimation();
        updatePos();
    }
}
