import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public abstract class Bonus implements IShip{


    protected IShip iShip;
    public Bonus(IShip iShip)
    {
        this.iShip=iShip;
    }
    @Override
    public void spawn() {

    iShip.spawn();

    }
    @Override
    public void moveLeft() {

        iShip.moveLeft();
    }

    @Override
    public void moveRight() {

        iShip.moveRight();
    }



    @Override
    public void getInstance() {

        iShip.getInstance();
    }



    @Override
    public Node getView() {
        return iShip.getView();
    }

    @Override
    public void setVelocity(double x, double y) {

        iShip.setVelocity(x,y);
    }

    @Override
    public double getxVelocity() {
        return iShip.getxVelocity();
    }

    @Override
    public void setxVelocity(double x) {

        iShip.setxVelocity(x);
    }

    @Override
    public int getHP() {
        return iShip.getHP();
    }

    @Override
    public void setHP(int HP) {

        iShip.setHP(HP);
    }

    @Override
    public IBullet getBullet() {
        return iShip.getBullet();
    }

    @Override
    public int getWeaponLevel() {
        return iShip.getWeaponLevel();
    }

    @Override
    public void setWeaponLevel(int weaponLevel) {
        iShip.setWeaponLevel(weaponLevel);

    }
    @Override
    public void gotHit(int power) {

    }
}
