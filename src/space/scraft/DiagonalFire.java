package space.scraft;

public class DiagonalFire extends Fire {
    Direction direction;
    public DiagonalFire(int x, int y, Direction direction) {
        super(x, y);
        this.direction = direction;
    }
    @Override
    public void update() {
        super.update();
        if (direction == Direction.UP) {
            setCurrentY(getCurrentY() - DEFAULT_SPEED); 
        }
        if (direction == Direction.DOWN) {
            setCurrentY(getCurrentY() + DEFAULT_SPEED); 
        }
    }
}
