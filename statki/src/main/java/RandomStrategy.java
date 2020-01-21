import javafx.geometry.Point2D;
import javafx.scene.Node;

public class RandomStrategy implements Strategy {

    public int move(Node view, Point2D velocity, IShip iShip) {
        if (Game.isTouchingBorder(view) && velocity.getX() > 0) {
            return -1;
        }
        if (Game.isTouchingBorder(view) && velocity.getX() < 00) {
            return 1;
        }
        if (velocity.getX() >= 0) return 1;
        if (velocity.getX() < 0) return -1;
        else return 1;
    }
}
