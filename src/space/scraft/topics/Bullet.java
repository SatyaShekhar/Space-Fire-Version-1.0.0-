/**
 * 
 */
package space.scraft.topics;

import space.scraft.Fire;
import space.scraft.SpaceScraft;
import space.scraft.SpaceScraftStarter;

/**
 * @author sbarik
 * 
 */
public class Bullet extends AbstractItems implements Enemy {
    public static final int WIDTH = 37;
    public static int HEIGHT = 20; // pixcel

    
    public Bullet(int x, int y, int speed, int health) {
        super(x, y, speed, health);
    }

    @Override
    public void update() {
        currentX = currentX - speed;
    }

    @Override
    public boolean colliding(SpaceScraft spaceScraft) {
        int scX = spaceScraft.getCurrentX();
        int scX2 = scX + SpaceScraft.IMAGE_WIDTH;
        int buX = getCurrentX();
        int buX2 = buX + WIDTH;
        if (scX > buX2 || scX2 < buX) {
            return false;
        }

        int scY = spaceScraft.getCurretY();
        int scY2 = scY + SpaceScraft.IMAGE_HEIGHT;
        int buY = getCurrentY();
        int buY2 = buY + HEIGHT;
        if (scY > buY2 || scY2 < buY) {
            return false;
        }
        return true;
    }

    @Override
    public void doCollideAction(SpaceScraft scraft) {
        // reduce life by one point
       scraft.getSpaceScraftStarter().setReinitializeGame(true);
    }

    @Override
    public boolean colliding(Fire fire) {

        int bX = getCurrentX();
        int bX2 = bX + Bullet.WIDTH;
        int fX = fire.getCurrentX();
        int fX2 = fX + Fire.IMAGE_WIDTH;
        if (bX > fX2 || bX2 < fX) {
            return false;
        }

        int bY = getCurrentY();
        int bY2 = bY + Bullet.HEIGHT;
        int fY = fire.getCurrentY();
        int fY2 = fY + Fire.IMAGE_HEIGHT;
        if (bY > fY2 || bY2 < fY) {
            return false;
        }
        return true;
    
    }
    
    @Override
    public int getImageWidth() {
        return WIDTH;
    }

    @Override
    public int getImageHeight() {
        return HEIGHT;
    }

    @Override
    public int getDefaultHealth() {
       return SpaceScraftStarter.level.getBulletHealth();
    }

}
