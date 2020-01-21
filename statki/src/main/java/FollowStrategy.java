import javafx.geometry.Point2D;
import javafx.scene.Node;

public class FollowStrategy implements Strategy {

    public double move(Node node, IShip iShip) {
        return 0;
    }

    @Override
    public int move(Node view, Point2D velocity, IShip iShip) {
        if (view.getTranslateX() < iShip.getView().getTranslateX()) {
            return 1;
        } else {
            return -1;
        }
    }
}
