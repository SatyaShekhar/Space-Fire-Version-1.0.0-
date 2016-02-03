package space.scraft;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import space.scraft.topics.Bullet;
import space.scraft.topics.Item;
import space.scraft.topics.Stone;

/**
 * TODO items :
 * 0. Pause, End game screen, restart option, Player name entry and score save option, number of bullets to be re evaluated
 * 1. Don't allow the flight move out of box (done)
 * 2. Maximizing should allow to play with large window or block re sizing of window
 * 3. Add score life and other menu items
 * 4. Add different other items to make it more interesting like (life, extra points, Fuel, flight health, target)
 * 5. images and back ground (transparent image and moving back ground) [Done]
 * 6. Sound (background and other movements)
 * 7. Bullet speed should increase after certain period of time and up to a specific limit (initial done)
 * 8. Levels (can be consider after one level is complete) (initial done)
 * 
 * @author sbarik
 * 
 */
public class SpaceScraftStarter extends Applet implements Runnable, KeyListener {
    public static final int MAX_FRAME_HEIGHT = 480;
    public static final int MAX_FRAM_WIDTH = 800;
    private static final long serialVersionUID = 1L;
    private List<Item> items = Collections.synchronizedList(new ArrayList<Item>());
    private List<Fire> fires = Collections.synchronizedList(new ArrayList<Fire>());

    // Not the best way of design but did for now to test
    public static GameLevel level = GameLevel.LevelOne;
    private boolean gamePaused = false;
    private URL base;
    private SpaceScraft spaceScraft;
    private static BackGround backGround1, backGround2;
    private Image image;
    private Graphics second;
    private int index = 0;
    private int score = 0;
    private boolean reinitializeGame;

    /**
     * Returns running copy base location (this url can be used to create images at run time
     * 
     * @param base
     */
    public URL getBase() {
        return this.base;
    }

    @Override
    public void init() {
        setSize(MAX_FRAM_WIDTH, MAX_FRAME_HEIGHT + 30);
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        getFrame().setTitle(level.getMessage());
        try {
            base = getDocumentBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        spaceScraft = new SpaceScraft(this);
        Image spacescraftImage = getImage(base, "data/space_scraft.png");

        spaceScraft.setImage(spacescraftImage);

        initialGameSetting(getFrame());
    }

    private void initialGameSetting(Frame frame) {
        backGround1 = new BackGround(0, 0);
        backGround1.setImage(getImage(base, level.getImageURL()));

        backGround2 = new BackGround(BackGround.width, 0);
        backGround2.setImage(getImage(base, level.getImageURL()));
        spaceScraft.setCurretY(frame.getHeight() / 2 - 20);
        spaceScraft.setCurrentX(0);
    }

    public void reInitalizedToDefault() {
        Frame frame = getFrame();
        items.clear();
        fires.clear();
        initialGameSetting(frame);
    }

    private void createAndAddBullet(Frame frame, int size) {
        // Generation of Enemy and Buster should be optimized
        int[] indexes = level.getIndexes();
        int width = frame.getWidth() - 60;
        for (int i = 0; i <= size; i++) {
            Item item;
            if (index == 20) {
                item = new Stone(width, indexes[index], level.getStoneSpeed(), level.getStoneHealth(), this);
            } else {
                if (level == GameLevel.LevelTwo || level == GameLevel.LevelThree || level == GameLevel.LevelFour) {
                    continue;
                }
                item = new Bullet(width, indexes[index], level.getBulletSpeed(), level.getBulletHealth());
                item.setImage(getImage(base, "data/bullet.png"));

            }
            items.add(item);
            
            index = (++index) % indexes.length;
        }
    }

    @Override
    public void update(Graphics g) {

        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }

        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backGround1.getImage(), backGround1.getCurrentX(), backGround1.getCurrentY(), this);
        g.drawImage(backGround2.getImage(), backGround2.getCurrentX(), backGround2.getCurrentY(), this);
        g.drawImage(spaceScraft.getImage(), spaceScraft.getCurrentX(), spaceScraft.getCurretY(), this);

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            g.drawImage(item.getImage(), item.getCurrentX(), item.getCurrentY(), this);
            ProgressBarHelper.display(g, item);
        }
        for (int i = 0; i < fires.size(); i++) {
            Fire fire = fires.get(i);
            g.drawImage(fire.getImage(), fire.getCurrentX(), fire.getCurrentY(), this);
        }
        displayStatus(g);
        if (spaceScraft.getLifes() < 0) {
            Font font = new Font(null, Font.BOLD | Font.ITALIC | Font.CENTER_BASELINE, 20);
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString("Game Over (Restart for new game)", MAX_FRAM_WIDTH / 2 - 150, MAX_FRAME_HEIGHT / 2);
        } else if (gamePaused) {
            Font font = new Font(null, Font.BOLD | Font.ITALIC | Font.CENTER_BASELINE, 20);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Game is PAUSED", MAX_FRAM_WIDTH / 2 - 150, MAX_FRAME_HEIGHT / 2);
        }
    }

    private void displayStatus(Graphics g) {
        Font font = new Font(null, Font.BOLD | Font.ITALIC, 15);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Your Score : " + score, MAX_FRAM_WIDTH - 150, 50);
        g.drawImage(getImage(base, "images/life.png"), 5, MAX_FRAME_HEIGHT - 3, this);
        font = new Font(null, Font.BOLD | Font.ITALIC, 16);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(Integer.toString(spaceScraft.getLifes()), 40, MAX_FRAME_HEIGHT + 10);
        int progressX = 70;
        int progressY = MAX_FRAME_HEIGHT + 3;
        ProgressBarHelper.displayBar(g, progressX, progressY, spaceScraft);
    }

    @Override
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gamePaused) {
            gamePaused = false;
        }
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            spaceScraft.moveUp();
            break;

        case KeyEvent.VK_DOWN:
            spaceScraft.moveDown();
            break;

        case KeyEvent.VK_LEFT:
            spaceScraft.moveLeft();
            break;

        case KeyEvent.VK_RIGHT:
            spaceScraft.moveRight();
            break;

        /* case KeyEvent.VK_SPACE:
             spaceScraft.fire(fires, getImage(base, "data/fire.png"));
             break;*/

        case KeyEvent.VK_CONTROL:
            gamePaused = true;
            break;
        default:
            gamePaused = true;
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            spaceScraft.stopVeritcalMove();
            break;

        case KeyEvent.VK_DOWN:
            spaceScraft.stopVeritcalMove();
            break;

        case KeyEvent.VK_LEFT:
            spaceScraft.stopHorizontalMove();
            break;

        case KeyEvent.VK_RIGHT:
            spaceScraft.stopHorizontalMove();
            break;

        case KeyEvent.VK_SPACE:
            break;

        case KeyEvent.VK_CONTROL:
            break;
        }

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // Nothing to be done here

    }

    @Override
    public void run() {
        int count = 1;
        while (true) {
            if (reinitializeGame) {
                spaceScraft.setLifes(spaceScraft.getLifes() - 1);
                reInitalizedToDefault();
                reinitializeGame = false;
            }
            try {
                Thread.sleep(37);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            LevelChangeHelper.upgradeLevel(this);

            if (spaceScraft.getLifes() < 0) {
                repaint();
                continue;
            }
            if (gamePaused) {
                repaint();
                continue;
            }
            backGround1.setSpeed(spaceScraft.getHorizontalSpeed());
            backGround2.setSpeed(spaceScraft.getHorizontalSpeed());
            backGround1.update();
            backGround2.update();
            spaceScraft.update();
            if (count++ % 6 == 0) {
                count = 1;
                spaceScraft.fire(fires, getImage(base, "data/fire.png"));
            }

            List<Item> itemssToBeRemoved = new ArrayList<Item>();
            List<Fire> firesToBeRemoved = new ArrayList<Fire>();
            // If bullets cross boundary remove or item is dead one
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                if (item.isDeadItem()) {
                    itemssToBeRemoved.add(item);
                    continue;
                }
                item.update();
                if (item.getCurrentX() < -60) {
                    itemssToBeRemoved.add(item);
                }
            }
            // TODO above items found not required now can be removed if its not further not required
            // If fire cross boundary remove
            for (int i = 0; i < fires.size(); i++) {
                Fire fire = fires.get(i);
                fire.update();
                if (fire.getCurrentX() + 10 > MAX_FRAM_WIDTH) {
                    firesToBeRemoved.add(fire);
                }
            }
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                if (item.colliding(spaceScraft)) {
                    item.doCollideAction(spaceScraft);
                }
            }

            // Check for collision enemy vs Fire
            for (int j = 0; j < fires.size(); j++) {
                Fire fire = fires.get(j);
                for (int i = 0; i < items.size(); i++) {
                    Item item = items.get(i);
                    if (!item.colliding(fire)) {
                        continue;
                    }
                    item.updateHealth();
                    if (item.getHealth() <= 0) {
                        itemssToBeRemoved.add(item);
                    }
                    firesToBeRemoved.add(fire);
                    score += 15;
                }
            }

            fires.removeAll(firesToBeRemoved);
            items.removeAll(itemssToBeRemoved);

            ItemCreater.createItems(items, level, spaceScraft);
            if (items.size() < 3) {
                createAndAddBullet(getFrame(), 3 - items.size());
            }

            repaint();
        }

    }

    public Frame getFrame() {
        return (Frame) this.getParent().getParent();
    }

    public boolean isReinitializeGame() {
        return reinitializeGame;
    }

    public void setReinitializeGame(boolean reinitializeGame) {
        this.reinitializeGame = reinitializeGame;
    }

    public SpaceScraft getSpaceScraft() {
        return spaceScraft;
    }

    public int getScore() {
        return score;
    }

    public GameLevel getLevel() {
        return level;
    }

    public void setLevel(GameLevel level) {
        SpaceScraftStarter.level = level;
        getFrame().setTitle(level.getMessage());
        backGround1 = new BackGround(0, 0);
        backGround1.setImage(getImage(base, level.getImageURL()));

        backGround2 = new BackGround(BackGround.width, 0);
        backGround2.setImage(getImage(base, level.getImageURL()));
    }

}