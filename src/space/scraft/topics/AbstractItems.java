package space.scraft.topics;

import java.awt.Image;

public abstract class AbstractItems implements Item {

    protected int currentX;
    protected int currentY;
    private Image image;
    protected boolean deadItem;
    protected int health = 1; // default can be over written by implementing class
    public int speed = 1;

   public AbstractItems(int x, int y, int speed, int health) {
    this.currentX = x;
    this.currentY = y;
    this.speed = speed;
    this.health = health;
}
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
    
    @Override
    public int getHealth() {
        return health;
    }
    
    @Override
    public void updateHealth() {
        health = health - 1;
    }
    @Override
    public boolean isDeadItem() {
        return deadItem;
    }

    @Override
    public int getCurrentX() {
        return currentX;
    }

    @Override
    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    @Override
    public int getCurrentY() {
        return currentY;
    }

    @Override
    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    public int getSpeed() {
        return speed;
    }
    
    
}
