import javafx.scene.Node;
import javafx.scene.image.Image;

public interface IShip {

//    int HP = 100;
//    int dx = 10, dy = 10;
//    BulletFactory bulletFactory = new BulletFactory();
//    Bullet bullet = BulletFactory.getBullet(0);
//    Ship shipInstance = Ship.getShipInstance();
//    Image image = new Image("/resources/ship/png");

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
