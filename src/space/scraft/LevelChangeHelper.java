package space.scraft;

/**
 * 
 * @author sbarik
 *
 */
public class LevelChangeHelper {

    public static void upgradeLevel(SpaceScraftStarter starter) {
        if (starter.getScore() < 2000) {
            return;
        }
        if (starter.getScore() < 7000) {
            if (starter.getLevel() != GameLevel.LevelTwo) {
                System.out.println("Level upgraded to " + GameLevel.LevelTwo);
                starter.setLevel(GameLevel.LevelTwo);
            }
            return;
        }
        if (starter.getScore() < 17000) {
            if (starter.getLevel() != GameLevel.LevelThree) {
                System.out.println("Level upgraded to " + GameLevel.LevelThree);
                starter.setLevel(GameLevel.LevelThree);
            }
            return;
        }

        if (starter.getScore() < 30000) {
            if (starter.getLevel() != GameLevel.LevelFour) {
                System.out.println("Level upgraded to " + GameLevel.LevelFour);
                starter.setLevel(GameLevel.LevelFour);
            }
            return;
        }
        
        if (starter.getScore() < 45000) {
            if (starter.getLevel() != GameLevel.LevelFive) {
                System.out.println("Level upgraded to " + GameLevel.LevelFive);
                starter.setLevel(GameLevel.LevelFive);
            }
            return;
        }
        
         throw new IllegalStateException("Game Over level not supported");

    }

}
