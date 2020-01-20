import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Ship implements IShip {

    private static Ship shipInstance;
    private static Image image;
    private static double WIDTH = 20;
    private static double HEIGTH = 40;
    int HP = 50;
    BulletFactory bulletFactory = new BulletFactory();
    IBullet iBullet;
    private double xVelocity = 2;
    private Node view;
    private Point2D velocity = new Point2D(0, 0);
    private boolean isAlive;
    private int weaponLevel = 1;

    private Ship() {
        this.view = new Rectangle(WIDTH, HEIGTH, Color.BLUE);
    }

    public static Ship getShipInstance() {
        if (shipInstance != null) {
            return shipInstance;
        } else {
            shipInstance = new Ship();
            return shipInstance;
        }
    }

    public int getWeaponLevel() {
        return weaponLevel;
    }

    public void setWeaponLevel(int weaponLevel) {
        this.weaponLevel = weaponLevel;
        this.iBullet = bulletFactory.getBullet(weaponLevel);
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public IBullet getBullet() {
        return iBullet;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
        this.velocity = new Point2D(xVelocity, 0);
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(double x, double y) {
        this.xVelocity = x;
        this.velocity = new Point2D(x, y);
    }

    public Node getView() {
        return view;
    }

    public void moveLeft() {
        if ((view.getTranslateX() >= 0))
            velocity = new Point2D(xVelocity * (-1), 0);
    }

    public void moveRight() {
        if ((view.getTranslateX() < (Game.getWIDTH() - this.WIDTH)))
            velocity = new Point2D(xVelocity, 0);
    }

    public IBullet shoot() {
        iBullet = bulletFactory.getBullet(weaponLevel);
        return iBullet;
    }

    private boolean isTouchingRightBorder() {
        if (view.getTranslateX() >= (Game.getWIDTH() - this.WIDTH)) {
            return true;
        } else return false;
    }

    private boolean isTouchingLeftBorder() {
        if (view.getTranslateX() <= 0) {
            return true;
        } else return false;
    }

    public void spawn() {

    }

    public void getInstance() {

    }

    public void draw() {
        System.out.println("draw");
//        isTouchingBorder()
        view.setTranslateX(view.getTranslateX() + velocity.getX());
        if (Game.isTouchingBorder(view)) {
            velocity = new Point2D(0, 0);
        }
    }

    public void update() {

    }
}
