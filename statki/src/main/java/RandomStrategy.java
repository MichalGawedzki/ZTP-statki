import javafx.geometry.Point2D;
import javafx.scene.Node;

public class RandomStrategy implements Strategy {

    public void move(Node view, double xVelocity, Point2D velocity, IShip iShip) {

        view.setTranslateX(view.getTranslateX() + velocity.getX());
        if (Game.isTouchingBorder(view)) {
            xVelocity = -xVelocity;
            velocity = new Point2D(xVelocity, 0);
        }
    }
}
