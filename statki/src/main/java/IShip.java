import javafx.scene.Node;
import javafx.scene.image.Image;

import java.io.IOException;

public interface IShip {

    void moveLeft();

    void moveRight();

    IBullet shoot();

    void spawn();

    void getInstance();

    static IShip getShipInstance() throws IOException {
        return Ship.getShipInstance();
    }

    void draw();

    Node getView();

    void setVelocity(double x, double y);

    double getxVelocity();

    void setxVelocity(double x);

    int getHP();

    void setOnlyVelocity(int x, int y);

    void setHP(int dx);

    IBullet getBullet();

    int getWeaponLevel();

    void setWeaponLevel(int weaponLevel);

    void gotHit(int power);
}
