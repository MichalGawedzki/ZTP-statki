import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Enemy{

    private int HP;
    private int frequency;
    private IBullet iBullet;
    private Strategy strategy;
    private Image image;
    private Node view;
    private IShip playerShip;
    private Point2D velocity;
    private double xVelocity;
    private boolean alive = true;

    public Enemy(){
        HP = 1;
        this.view = new Rectangle(20, 40, Color.VIOLET);
        view.setTranslateY(10);
        view.setTranslateX(Math.random()*Game.getWIDTH());
        xVelocity = Math.random();
        velocity = new Point2D(xVelocity, 0);
//        view.setTranslateX(11);
    }

    public void draw(Pane root){
//        root.getChildren().remove(view);
////        root.getChildren().add(view);


        view.setTranslateX(view.getTranslateX() + velocity.getX());
        if (Game.isTouchingBorder(view)) {
            xVelocity = -xVelocity;
            velocity = new Point2D(xVelocity, 0);
        }
    }

    public void gotHit(){
        this.HP -= IShip.getShipInstance().getBullet().getPower();
    }

    public Node getView() {
        return view;
    }

    public boolean isAlive() {
        if(HP<=0)
        alive = false;
        return alive;
    }
}
