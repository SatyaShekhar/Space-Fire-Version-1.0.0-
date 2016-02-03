/**
 * 
 */
package space.scraft.topics;

import java.awt.Image;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import space.scraft.Fire;
import space.scraft.SpaceScraft;
import space.scraft.SpaceScraftStarter;

/**
 * @author sbarik
 * 
 */
public class Stone extends AbstractItems implements Enemy {
    public static final int WIDTH = 98;
    public static int HEIGHT = 70; // pixcel
    private int TOTAL_IMAGES = 15;
    private int currentImageIndex = 1;
    private int counter = 1;
    private Map<Integer, Image> mapOfImages = new HashMap<>();
    
    public Stone(int x, int y, int speed, int health, SpaceScraftStarter starter) {
        super(x, y, speed, health);
        URL baseLocatoin = starter.getBase();
        int i = 0;
        for ( ; i < 8 ; i ++) {
            mapOfImages.put(i, starter.getImage(baseLocatoin, "images/stones/stone" + i + ".png"));
        }
        for (int j = 8; j < TOTAL_IMAGES; j++) {
            mapOfImages.put(j, mapOfImages.get((--i)));
        }
    }
    
    @Override
    public Image getImage() {
        return mapOfImages.get(currentImageIndex);
    }
    
    @Override
    public void update() {
        currentX = currentX - speed;
        counter = (++counter) % 12;
        if (counter == 0) {
            currentImageIndex = (currentImageIndex + 1) % TOTAL_IMAGES;
        }
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
        if (deadItem) {
            return;
        }
       scraft.getSpaceScraftStarter().setReinitializeGame(true);
       deadItem = true;
    }

    @Override
    public boolean colliding(Fire fire) {

        int bX = getCurrentX();
        int bX2 = bX + Stone.WIDTH;
        int fX = fire.getCurrentX();
        int fX2 = fX + Fire.IMAGE_WIDTH;
        if (bX > fX2 || bX2 < fX) {
            return false;
        }

        int bY = getCurrentY();
        int bY2 = bY + Stone.HEIGHT;
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
        return SpaceScraftStarter.level.getStoneHealth();
    }

}
