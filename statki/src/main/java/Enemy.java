import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.time.LocalTime;
import java.util.Random;

public class Enemy {

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
    BulletFactory bulletFactory;
    private double shootFrequency; // time between each shoot in miliseconds
    private int max = 5;
    private int min = 2;
    private int range = max - min + 1;

    private LocalTime spawnTime;

    public int getHP() {
        return HP;
    }

    public Enemy(int level) {
        HP = level * 2;

        Image image = new Image("img/enemy1.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        this.view = imageView;

//        this.view = new Rectangle(20, 40, Color.VIOLET);
        bulletFactory = Game.getGame().getBulletFactory();
        view.setTranslateY(10);
        view.setTranslateX(Math.random() * (Game.getWIDTH() - view.getBoundsInParent().getWidth()));
        xVelocity = Math.random() * 4 - 1;
        velocity = new Point2D(xVelocity, 0);
        shootFrequency = ((Math.random() * range) + min) * 1000;
        spawnTime = LocalTime.now();
//        view.setTranslateX(11);
        if (level <= 4) strategy = new RandomStrategy();
        else strategy = new FollowStrategy();
    }

    public void draw(Pane root) {
//        root.getChildren().remove(view);
////        root.getChildren().add(view);


//        strategy.move(view, velocity, IShip.getShipInstance());
//        xVelocity *= strategy.move(view, velocity, IShip.getShipInstance());
//        velocity = new Point2D(xVelocity, 0);
//        view.setTranslateX(view.getTranslateX() + velocity.getX());
//        if (Game.isTouchingBorder(view)) {
//            xVelocity = -xVelocity;
//            velocity = new Point2D(xVelocity, 0);
//        }

        int direction = strategy.move(view, velocity, IShip.getShipInstance());
        xVelocity = Math.abs(xVelocity) * direction;
        velocity = new Point2D(xVelocity, 0);
        view.setTranslateX(view.getTranslateX() + xVelocity);

//        view.setTranslateX(view.getTranslateX() + velocity.getX());
//        if (Game.isTouchingBorder(view)) {
//            xVelocity = -xVelocity;
//            velocity = new Point2D(xVelocity, 0);
//        }
    }

    public void gotHit() {
        this.HP -= IShip.getShipInstance().getBullet().getPower();
    }

    public Node getView() {
        return view;
    }

    public boolean isAlive() {
        if (HP <= 0)
            alive = false;
        return alive;
    }

    public IBullet shoot() {
        iBullet = bulletFactory.getBullet(-2);
        return iBullet;
    }

    public double getShootFrequency() {
        return shootFrequency;
    }

    public void setShootFrequency(double shootFrequency) {
        this.shootFrequency = shootFrequency;
    }

    public LocalTime getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(LocalTime spawnTime) {
        this.spawnTime = spawnTime;
    }
}