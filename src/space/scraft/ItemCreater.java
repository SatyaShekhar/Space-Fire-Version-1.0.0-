/**
 * 
 */
package space.scraft;

import java.awt.Frame;
import java.net.URL;
import java.util.List;

import space.scraft.topics.Bullet;
import space.scraft.topics.Fuel;
import space.scraft.topics.Item;
import space.scraft.topics.LifeBooster;

/**
 * @author Satya Shekhar Barik
 * 
 */
public class ItemCreater {
    private static int createrCount = 1;
    private static int bulletGroupIndex = 0;

    public static void createItems(List<Item> items, GameLevel level, SpaceScraft spaceScraft) {
        int fuelCreaterIndex = 500;
        int lifeCreaterIndex = 1100;

        SpaceScraftStarter starter = spaceScraft.getSpaceScraftStarter();
        Frame frame = starter.getFrame();
        int width = frame.getWidth() - 60;
        URL base = starter.getBase();

        if (level == GameLevel.LevelTwo || level == GameLevel.LevelThree || level == GameLevel.LevelFour) {
            if (createrCount % 200 == 0) {
                List<Coordinate> bullets = level.getBulletCoordinates(bulletGroupIndex, frame);
                for (Coordinate coordinate : bullets) {
                    Item item = new Bullet(coordinate.getX(), coordinate.getY(), level.getBulletSpeed(), level.getBulletHealth());
                    item.setImage(starter.getImage(base, "data/bullet.png"));
                    items.add(item);
                }
                bulletGroupIndex = (bulletGroupIndex + 1) % 6;
            }
        }

        if (createrCount % fuelCreaterIndex == 0) {
            // Fuel creator
            Item item = new Fuel(width, 250, level.getFuleSpeed());
            item.setImage(starter.getImage(base, "images/fuel.png"));
            items.add(item);
        }

        if (createrCount % lifeCreaterIndex == 0) {
            createLifeSorroundedByBullet(items, level, starter, width, base);
        }
        createrCount = (createrCount + 1) % 10000;
        if (createrCount == 0) {
            createrCount = 1;
        }
    }

    private static void createLifeSorroundedByBullet(List<Item> items, GameLevel level, SpaceScraftStarter starter, int width, URL base) {
        int height = 100;
        Item item = new LifeBooster(width, height, level.getLifeSpeed());
        item.setImage(starter.getImage(base, "images/life.png"));
        Bullet bullet = new Bullet(width - 50, height, level.getLifeSpeed(), level.getBulletHealth()); // TODO better to use fixed speed
        bullet.setImage(starter.getImage(base, "images/bulletLifeProtector.png"));
        Bullet bullet2 = new Bullet(width + 50, height, level.getLifeSpeed(), level.getBulletHealth()); 
        bullet2.setImage(starter.getImage(base, "images/bulletLifeProtector.png"));
        Bullet bullet3 = new Bullet(width, height - 50, level.getLifeSpeed(), level.getBulletHealth()); 
        bullet3.setImage(starter.getImage(base, "images/bulletLifeProtector.png"));
        Bullet bullet4 = new Bullet(width, height + 50, level.getLifeSpeed(), level.getBulletHealth()); 
        bullet4.setImage(starter.getImage(base, "images/bulletLifeProtector.png"));
        items.add(item);
        items.add(bullet);
        items.add(bullet2);
        items.add(bullet3);
        items.add(bullet4);
    }

}
