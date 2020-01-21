import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class Bullet implements IBullet {

    int index;

    public int getPower() {
        return power;
    }

    int power;
    int velocity;
    Color color;
    private String pathToImg;
    Image image;


    Bullet(int index) {
        this.index = index;
        if (index <= -5) {
            power = 5;
            velocity = 4;
            color = Color.PURPLE;
            pathToImg = "img/bullet_enemy.png";
            System.out.println("new Bullet object PURPLE");
        } else if (index == -3 || index == -4) {
            power = 4;
            velocity = 3;
            color = Color.PURPLE;
            pathToImg = "img/bullet_enemy.png";
            System.out.println("new Bullet object PURPLE");
        } else if (index == -1 || index == -2) {
            power = 3;
            velocity = 3;
            color = Color.PURPLE;
            pathToImg = "img/bullet_enemy.png";
            System.out.println("new Bullet object PURPLE");
        } else if (index == 0) {
            power = 20;
            velocity = -5;
            color = Color.PURPLE;
            pathToImg = "img/bullet_premium.png";
            System.out.println("new SUPA BULLET");
        } else if (index == 1 || index == 2) {
            power = 1;
            velocity = -1;
            color = Color.GREEN;
            pathToImg = "img/bullet_yellow.png";
            System.out.println("new Bullet object GREEN");
        } else if (index == 3 || index == 4) {
            power = 2;
            velocity = -2;
            color = Color.CORAL;
            pathToImg = "img/bullet_gold.png";
            System.out.println("new Bullet object CORAL");
        } else if (index >= 5) {
            power = 3;
            velocity = -3;
            color = Color.RED;
            pathToImg = "img/bullet_blue.png";
            System.out.println("new Bullet object RED");
        }
        image = new Image(pathToImg);
    }


    @Override
    public Node draw(Node view) {

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        if (index < 0) {
            imageView.setRotate(135);
        }
        if (index >= 5) {
            imageView.setRotate(-45);
        }
        Node newNode = imageView;

        newNode.setTranslateY(view.getTranslateY() + velocity);
        newNode.setTranslateX(view.getTranslateX());

        return newNode;
    }

    @Override
    public Node spawn(double xx, double yy) throws IOException {

        Node view = new Circle(5, 5, 5, Color.RED);
        view.setTranslateX(xx + IShip.getShipInstance().getView().getBoundsInParent().getWidth() / 4);
        view.setTranslateY(yy);
        return view;

    }
}
