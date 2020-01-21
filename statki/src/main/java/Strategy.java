import javafx.geometry.Point2D;
import javafx.scene.Node;

public interface Strategy {

    int move(Node node, Point2D velocity, IShip iShip);

}
