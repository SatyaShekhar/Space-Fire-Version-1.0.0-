/**
 * 
 */
package space.scraft.topics;

import space.scraft.Fire;
import space.scraft.SpaceScraft;

/**
 * @author sbarik
 *
 */
public class LifeBooster extends AbstractItems {
    public static final int WIDTH = 25;
    public static int HEIGHT = 23;
    public boolean increaseLife = false;
    
    public LifeBooster(int x, int y, int speed) {
        // Health 1 as default
        super(x, y, speed, 1);
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
        if (deadItem) {
            return;
        }
        scraft.increaseLifeByOne();
        // Don't not allow to increase life mor than once so mark it dead
        deadItem = true;
    }

    @Override
    public boolean colliding(Fire fire) {
        // Never check for colission with fire as not required
        return false;
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
        throw new IllegalStateException("Health not supported for this object");
    }
    
}
