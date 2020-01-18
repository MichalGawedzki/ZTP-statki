import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Ship implements IShip{

    private static Ship shipInstance;
    private static Image image;

    private Node view;

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(double x, double y) {
        this.velocity = new Point2D(x, y);
    }

    private Point2D velocity = new Point2D(0, 0);
    private boolean isAlive;

//    static{
//        try{
//            image = Game.loadImage("img/ship.png");
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }


    public Node getView() {
        return view;
    }

    private Ship(){
        this.view = new Rectangle(20, 40, Color.BLUE);
    }

    public static Ship getShipInstance() {
        if (shipInstance != null) {
            return shipInstance;
        }else{
            shipInstance = new Ship();
            return shipInstance;
        }
    }

    public void move() {
        System.out.println("move");
    }

    public void shoot() {
        System.out.println("shoot");
    }

    public void spawn() {

    }

    public void getInstance() {

    }

    public void draw() {
        view.setTranslateX(view.getTranslateX() + velocity.getX());
        view.setTranslateY(view.getTranslateY() + velocity.getY());
    }

    public void update(){

    }
}
