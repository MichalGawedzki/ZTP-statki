import javafx.scene.Node;
import javafx.scene.image.Image;

public interface IShip {

    void moveLeft();

    void moveRight();

    IBullet shoot(int weaponLevel);

    void spawn();

    void getInstance();

    static IShip getShipInstance() {
        return Ship.getShipInstance();
    }

    void draw();

    Node getView();

    void setVelocity(double x, double y);

    double getxVelocity();

    void setxVelocity(double x);

    int getHP();

    void setHP(int dx);

    IBullet getBullet();


}
