package space.scraft;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sbarik
 * 
 */
public enum GameLevel {
    LevelOne("Level 1 : Cross the distance", 1, 3, 2, 2, 2, 10, "background/background.png") { 
        @Override
        public int[] getIndexes() {
            int[] idexes = { 270, 125, 240, 60, 399, 190, 450, 100, 360, 10, 210, 330, 435, 80, 310,
                                160, 380, 40, 356, 120, 290 };
            return idexes;
        }
    },
    LevelTwo("Level 2 : Cross the fire", 2, 3, 6, 2, 3, 30, "background/background2.png") {
        @Override
        public List<Coordinate> getBulletCoordinates(int index, Frame frame) {
            
            Map<Integer, List<Coordinate>> mapOfBullets = new HashMap<>();
            
            List<Coordinate> value1 = new ArrayList<>();
            value1.add(new Coordinate(frame.getWidth() - 10, 120));
            value1.add(new Coordinate(frame.getWidth() + 20, 160));
            value1.add(new Coordinate(frame.getWidth() + 20, 80));
            value1.add(new Coordinate(frame.getWidth() + 40, 200));
            value1.add(new Coordinate(frame.getWidth() + 40, 40));
            
            List<Coordinate> value2 = new ArrayList<>();
            value2.add(new Coordinate(frame.getWidth() - 10, 200));
            value2.add(new Coordinate(frame.getWidth() + 50, 240));
            value2.add(new Coordinate(frame.getWidth() + 110, 280));
            value2.add(new Coordinate(frame.getWidth() + 160, 320));
            value2.add(new Coordinate(frame.getWidth() + 200, 360));
            
            List<Coordinate> value3 = new ArrayList<>();
            value3.add(new Coordinate(frame.getWidth() - 20, 350));
            value3.add(new Coordinate(frame.getWidth() + 50, 350));
            value3.add(new Coordinate(frame.getWidth() + 100, 350));
            value3.add(new Coordinate(frame.getWidth() + 50, 300));
            value3.add(new Coordinate(frame.getWidth() + 50, 400));
            
            List<Coordinate> value4 = new ArrayList<>();
            value4.add(new Coordinate(frame.getWidth() - 10, 180));
            value4.add(new Coordinate(frame.getWidth() - 10, 220));
            value4.add(new Coordinate(frame.getWidth() - 10, 260));
            value4.add(new Coordinate(frame.getWidth() - 10, 300));
            value4.add(new Coordinate(frame.getWidth() - 10, 340));
            value4.add(new Coordinate(frame.getWidth() - 10, 380));
            value4.add(new Coordinate(frame.getWidth() + 150, 180));
            value4.add(new Coordinate(frame.getWidth() + 150, 220));
            value4.add(new Coordinate(frame.getWidth() + 150, 260));
            value4.add(new Coordinate(frame.getWidth() + 150, 300));
            value4.add(new Coordinate(frame.getWidth() + 150, 340));
            value4.add(new Coordinate(frame.getWidth() + 150, 380));
            
            
            mapOfBullets.put(0, value1 );
            mapOfBullets.put(1, value2);
            mapOfBullets.put(2, value3);
            mapOfBullets.put(3, value4);
            mapOfBullets.put(4, value1);
            mapOfBullets.put(5, value2);
            
            return mapOfBullets.get(index);
        }
    } ,
    LevelThree("Level 3 : Kill the demons", 3, 3, 10, 3, 4, 32, "background/background.png"),
    LevelFour("Level 4 : ???????????????", 3, 3, 6, 10, 6, 30, "background/background2.png"),
    LevelFive("Level 5 : XXXXXXXXXXXXXXXX", 5, 3, 5, 5, 7, 30, "background/background.png");

    private String message;
    private byte bulletSpeed;
    private byte lifeSpeed;
    private byte fuleSpeed;
    private byte stoneSpeed;
    private byte bulletHealth;
    private byte stoneHealth;
    private String imageURL;

    /**
     * 
     * @param message
     * @param bulletSpeed
     * @param lifeSpeed
     * @param fuleSpeed
     * @param stoneSpeed
     * @param bulletHealth
     * @param stoneHealth
     */
    private GameLevel(String message, int bulletSpeed, int lifeSpeed, int fuleSpeed, int stoneSpeed, int bulletHealth, int stoneHealth, String url) {
        this.message = message;
        this.bulletSpeed = (byte) bulletSpeed;
        this.lifeSpeed = (byte) lifeSpeed;
        this.fuleSpeed = (byte) fuleSpeed;
        this.stoneSpeed = (byte) stoneSpeed;
        this.bulletHealth = (byte) bulletHealth;
        this.stoneHealth = (byte) stoneHealth;
        this.imageURL = url;
    }

    public String getMessage() {
        return message;
    }

    public void upgrade(SpaceScraftStarter starter) {
        starter.setLevel(this);

    }

    public byte getBulletSpeed() {
        return bulletSpeed;
    }

    public byte getLifeSpeed() {
        return lifeSpeed;
    }

    public byte getFuleSpeed() {
        return fuleSpeed;
    }

    public byte getStoneSpeed() {
        return stoneSpeed;
    }

    public byte getBulletHealth() {
        return bulletHealth;
    }

    public byte getStoneHealth() {
        return stoneHealth;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int[] getIndexes() {
        int[] idexes = { 270, 125, 240, 60, 399, 190, 450, 100, 360, 10, 210, 330, 435, 80, 310,
                         160, 380, 40, 356, 120, 290 };
        return idexes;
    }
    
    public List<Coordinate> getBulletCoordinates(int index, Frame frame) {
        throw new IllegalStateException("getBulletCoordinates(index) not implemented for " + this);
    }
}
