import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet implements IBullet {

    int index;
    int power;
    int velocity;
    private Node view;

    Bullet(int index) {
        this.index = index;
        view = new Circle(5, 5, 5, Color.RED);
        if (index == 0) {
            power = 5;
            velocity = 1;
        } else if (index == 1) {
            power = 10;
            velocity = 2;
        } else {
            power = 15;
            velocity = 3;
        }
    }

    public Node getView() {
        return view;
    }

    public void setView(Node view) {
        this.view = view;
    }

    @Override
    public void draw() {

    }

    public void spawn(){

    }
}
