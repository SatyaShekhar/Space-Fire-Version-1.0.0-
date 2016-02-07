package space.scraft;

import java.awt.Image;

/**
 * 
 * @author Satya Shekhar Barik
 *
 */
public class BackGround {

    private static int DEFAULT_SPEED = 2;
    public static int width = 2160 - 50;
    private int currentX;
    private int currentY;
    private int speed = DEFAULT_SPEED;
    private Image image;
    
    public BackGround(int x, int y) {
        this.currentX = x;
        this.currentY = y;
    }
    
    public void update() {
        if (speed <= 0) {
            speed = DEFAULT_SPEED;
        }
        currentX = currentX - speed;
        if ((currentX) <= - width ){
            currentX = width - 20;
         }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    
}
