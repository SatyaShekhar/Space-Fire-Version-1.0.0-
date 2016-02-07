package space.scraft;

import java.awt.Image;

/**
 * 
 * @author Satya Shekhar Barik
 *
 */
public class Fire {

    public static final int IMAGE_WIDTH = 21;
    public static int DEFAULT_SPEED = 10;
    public static int IMAGE_HEIGHT = 21; // pixcel
    private int currentX;
    private int currentY;
    private Image image;
    
    public Fire(int x, int y) {
        this.currentX = x;
        this.currentY = y;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public void update() {
        currentX = currentX + DEFAULT_SPEED;
    }
}
