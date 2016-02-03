package space.scraft.topics;

import java.awt.Image;

import space.scraft.Fire;
import space.scraft.SpaceScraft;

/**
 * TODO : Class name can be more realistic
 * @author sbarik
 *
 */
public interface Item {
    public void update();
    public int getCurrentX();
    public void setCurrentX(int currentX);
    public int getCurrentY();
    public void setCurrentY(int currentY);
    public Image getImage();
    public void setImage(Image image);
    public boolean colliding(SpaceScraft spaceScraft);
    public void doCollideAction(SpaceScraft scraft);
    public int getImageWidth();
    public int getImageHeight();
    public int getDefaultHealth();
    /**
     * Return true if there is a collision with fires from Space scraft and its going to make any impact to that
     * 
     * Note : For life and fuel it will return false as the collision does not mean any thing for them
     * 
     * @param fire fired instance from Space scraft
     * @return
     */
    public boolean colliding(Fire fire);
    boolean isDeadItem();
    // TODO rename to decrease health (not changing having problem with eclipse not sure
    public void updateHealth();
    public int getHealth();
}
