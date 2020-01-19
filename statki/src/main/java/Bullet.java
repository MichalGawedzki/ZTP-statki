import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet implements IBullet {

    int index;

    public int getPower() {
        return power;
    }

    int power;
    int velocity;
//    private Node view;
    Color color;

    Bullet(int index) {
        this.index = index;
//        view = new Circle(5, 5, 5, Color.RED);
        if (index == 1) {
            power = 1;
            velocity = -1;
            color = Color.GREEN;
            System.out.println("new Bullet object GREEN");
        } else if (index == 2) {
            power = 2;
            velocity = -2;
            color = Color.CORAL;
            System.out.println("new Bullet object CORAL");
        } else {
            power = 3;
            velocity = -3;
            color = Color.RED;
            System.out.println("new Bullet object RED");
        }

    }


    @Override
    public Node draw(Node view) {
        Node newNode = new Circle(5, 5, 5, color);
        newNode.setTranslateY(view.getTranslateY() + velocity);
        newNode.setTranslateX(view.getTranslateX());
        return newNode;
    }

    @Override
    public Node spawn() {
//        double xx = iShip.getView().getTranslateX();
//        double yy = iShip.getView().getTranslateY();
//        Node startingPosition = new Circle(5, 5, 5, Color.RED);
//        startingPosition.setTranslateX(xx);
//        startingPosition.setTranslateY(yy);
//        bulletList.put(startingPosition, iShip.shoot(weaponLevel));
//        addBullet(startingPosition);
////                addBullet(iShip);
//        System.out.println(bulletList);


        double xx = IShip.getShipInstance().getView().getTranslateX();
        double yy = IShip.getShipInstance().getView().getTranslateY();
        Node view = new Circle(5, 5, 5, Color.RED);
        view.setTranslateX(xx);
        view.setTranslateY(yy);
        return view;



//        view.setTranslateX(view.getTranslateX() + velocity.getX());
//        if (isTouchingLeftBorder() || isTouchingRightBorder()) {
//            velocity = new Point2D(0, 0);
//        }
    }
}
