import javafx.geometry.Point2D;
import javafx.scene.Node;

public interface Strategy {

    void move(Node node, double xVelocity, Point2D velocity, IShip iShip);

}

