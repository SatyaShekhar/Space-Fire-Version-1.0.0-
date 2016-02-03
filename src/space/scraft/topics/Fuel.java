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
public class Fuel extends AbstractItems {
    public static final int WIDTH = 25;
    public static int HEIGHT = 23;
    public Fuel(int x, int y, int speed) {
        // default 1 for health
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
        scraft.increaseHealth();
        deadItem = true;
    }

    @Override
    public boolean colliding(Fire fire) {
        // Never check for collision with fire not relevant
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
