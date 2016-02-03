package space.scraft;

import java.awt.Color;
import java.awt.Graphics;

import space.scraft.topics.Enemy;
import space.scraft.topics.Item;

public class ProgressBarHelper {

    public static void displayBar(Graphics g, int progressX, int progressY, SpaceScraft spaceScraft) {
        int width = 200;
        int health = spaceScraft.getHealth();
        setGraphicsColor(g, health/10);
        g.draw3DRect(progressX, progressY, width, 15, true);
        g.fillRect(progressX, progressY, health / 5, 15);
    }

    public static void display(Graphics g, Item item) {
        if(!(item instanceof Enemy)) {
            return;
        }
        int x = item.getCurrentX() + item.getImageWidth() - (item.getImageWidth() / 3);
        int y = item.getCurrentY() - 3;
        float defaultHealth = item.getDefaultHealth();
        int health = item.getHealth();
        int width =25;
        int height = 7;
        
        setGraphicsColor(g, item.getHealth() * 10);
        g.draw3DRect(x, y, width, height, true);
        g.fillRect(x, y, (int) ((health/defaultHealth) * width), height);
        
        
        
    }
    /**
     * 
     * @param g graphics instance
     * @param percentage out of 100
     */
    private static void setGraphicsColor(Graphics g, int percentage) {
        if (percentage < 25) {
            g.setColor(Color.RED);
            return;
        }
        if (percentage < 50) {
            g.setColor(Color.YELLOW);
            return;
        }
        g.setColor(Color.GREEN);
    }
}
