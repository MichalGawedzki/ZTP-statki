import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Random;

public class Enemy {

    private int HP;
    private IBullet iBullet;
    private Strategy strategy;
    private Node view;
    private Point2D velocity;
    private double xVelocity;
    private boolean alive = true;
    BulletFactory bulletFactory;
    private double shootFrequency;
    private int max = 5;
    private int min = 2;
    private int range = max - min + 1;

    private LocalTime spawnTime;
    int level;

    public Enemy(int level) throws IOException {
        this.level = level;
        HP = level * 2;

        Image image = new Image("img/enemy1.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        this.view = imageView;

        bulletFactory = Game.getGame().getBulletFactory();
        view.setTranslateY(10);
        view.setTranslateX(Math.random() * (Game.getWIDTH() - view.getBoundsInParent().getWidth()));
        xVelocity = Math.random() * 4 - 1;
        velocity = new Point2D(xVelocity, 0);
        shootFrequency = ((Math.random() * range) + min) * 1000;
        spawnTime = LocalTime.now();

        if (level <= 4) strategy = new RandomStrategy();
        else strategy = new FollowStrategy();
    }

    public void draw(Pane root) throws IOException {

        int direction = strategy.move(view, velocity, IShip.getShipInstance());
        xVelocity = Math.abs(xVelocity) * direction;
        velocity = new Point2D(xVelocity, 0);
        view.setTranslateX(view.getTranslateX() + xVelocity);
    }

    public void gotHit() throws IOException {
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
        iBullet = bulletFactory.getBullet(level * (-1));
        return iBullet;
    }

    public double getShootFrequency() {
        return shootFrequency;
    }

    public LocalTime getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(LocalTime spawnTime) {
        this.spawnTime = spawnTime;
    }
}
