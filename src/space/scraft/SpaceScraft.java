/**
 * 
 */
package space.scraft;

import java.awt.Image;
import java.util.List;

import space.scraft.topics.Bullet;
import space.scraft.topics.Item;

/**
 * @author Satya Shekhar Barik
 * 
 */
public class SpaceScraft {

    private static final int VERTICAL_INCREMENT = 5;
    private static final int HORIZONTAL_INCREMENT = 5;
    public static final int DEFAULT_HEALTH = 1000;
    public static int IMAGE_HEIGHT = 61; // pixcel
    public static int IMAGE_WIDTH = 140; // pixcel

    private int currentX = 100;
    private int currentY = 100;
    private int verticalSpeed;
    private int horizontalSpeed;
    private Image image;

    private int lifes;
    private int health;
    private SpaceScraftStarter spaceScraftStarter;
    
    private boolean upperBullet = false;

    public SpaceScraft(SpaceScraftStarter spaceScraftStarter) {
        this.spaceScraftStarter = spaceScraftStarter;
        health = DEFAULT_HEALTH;
    }

    public SpaceScraftStarter getSpaceScraftStarter() {
        return spaceScraftStarter;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
        //spaceScraftStarter.setReinitializeGame(true);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void moveUp() {
        verticalSpeed = -VERTICAL_INCREMENT;
    }

    public void moveDown() {
        verticalSpeed = VERTICAL_INCREMENT;
    }

    public void moveRight() {
        horizontalSpeed = HORIZONTAL_INCREMENT;
    }

    public void moveLeft() {
        horizontalSpeed = -HORIZONTAL_INCREMENT;
    }

    public void stopHorizontalMove() {
        horizontalSpeed = 0;
    }

    public void stopVeritcalMove() {
        verticalSpeed = 0;
    }

    public void update() {
        //System.out.println("horizontalSpeed" + horizontalSpeed + " verticalSpeed" + verticalSpeed);

        currentX = currentX + horizontalSpeed;
        currentY = currentY + verticalSpeed;
        if (currentY <= 0) {
            currentY = 0;
        }
        if (currentX < 0) {
            currentX = 0;
        }
        if ((currentX + IMAGE_WIDTH) >= SpaceScraftStarter.MAX_FRAM_WIDTH) {
            currentX = SpaceScraftStarter.MAX_FRAM_WIDTH - IMAGE_WIDTH;
        }
        if (currentY + IMAGE_HEIGHT >= SpaceScraftStarter.MAX_FRAME_HEIGHT) {
            currentY = SpaceScraftStarter.MAX_FRAME_HEIGHT - IMAGE_HEIGHT;
        }
        health = health - 1;
        if (health <= 0) {
            lifes = lifes - 1;
            health = DEFAULT_HEALTH;
        }
    }

    public void setImage(Image spacescraftImage) {
        this.image = spacescraftImage;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurretY() {
        return currentY;
    }

    public void setCurretY(int curretY) {
        this.currentY = curretY;
    }

    public int getVerticalSpeed() {
        return verticalSpeed;
    }

    public void setVerticalSpeed(int verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public int getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public void setHorizontalSpeed(int horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    public static int getVerticalIncrement() {
        return VERTICAL_INCREMENT;
    }

    public static int getHorizontalIncrement() {
        return HORIZONTAL_INCREMENT;
    }

    public Image getImage() {
        return image;
    }

    public void fire(List<Fire> fires, Image image) {
        int x = currentX + IMAGE_WIDTH - 5;
        int y = currentY + IMAGE_HEIGHT / 2 - 10;
        Fire fire = new Fire(x, y);
        fire.setImage(image);
        fires.add(fire);
        if (SpaceScraftStarter.level != GameLevel.LevelOne) {
            Fire fire2 = new Fire(x-10, y-30);
            fire2.setImage(image);
            fires.add(fire2);
        }

        /*Fire fireup = new DiagonalFire(x - 50, y, Direction.UP);
        fireup.setImage(image);
        fires.add(fireup);
        Fire firedown = new DiagonalFire(x - 50, y, Direction.DOWN);
        firedown.setImage(image);
        fires.add(firedown);*/
    }

    @Deprecated
    public boolean colliding(Item bullet) {
        int scX = getCurrentX();
        int scX2 = scX + SpaceScraft.IMAGE_WIDTH;
        int buX = bullet.getCurrentX();
        int buX2 = buX + Bullet.WIDTH;
        if (scX > buX2 || scX2 < buX) {
            return false;
        }

        int scY = getCurretY();
        int scY2 = scY + SpaceScraft.IMAGE_HEIGHT;
        int buY = bullet.getCurrentY();
        int buY2 = buY + Fire.IMAGE_HEIGHT;
        if (scY > buY2 || scY2 < buY) {
            return false;
        }
        return true;
    }

    public void increaseHealth() {
        this.health = DEFAULT_HEALTH; // in percentage
    }

    public void increaseLifeByOne() {
        lifes = lifes + 1;
    }

    /**
     * @return the upperBullet
     */
    public boolean isUpperBullet() {
        return upperBullet;
    }

    /**
     * @param upperBullet the upperBullet to set
     */
    public void setUpperBullet(boolean upperBullet) {
        this.upperBullet = upperBullet;
    }
}
