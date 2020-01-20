import javafx.geometry.Point2D;
import javafx.scene.Node;

public class FollowStrategy implements Strategy{

    public double move(Node node, IShip iShip) {
        return 0;
    }

    @Override
    public void move(Node node, double xVelocity, Point2D velocity, IShip iShip) {

    }
}
