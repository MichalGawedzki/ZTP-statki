import javafx.geometry.Point2D;
import javafx.scene.Node;

public class FollowStrategy implements Strategy {

    @Override
    public int move(Node view, Point2D velocity, IShip iShip) {
        if (view.getTranslateX() < iShip.getView().getTranslateX()) {
            return 1;
        } else {
            return -1;
        }
    }
}
