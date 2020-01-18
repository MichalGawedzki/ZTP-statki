import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Game {

    private static final int HEIGTH = 500;
    private static final int WIDTH = 500;
    private static Stage window;
    private static Game game = new Game();
    private Scene scene;
    private Pane root;
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private ArrayList<Bonus> bonusList = new ArrayList<Bonus>();
    private HashMap<Node, IBullet> bulletList = new HashMap<Node, IBullet>();
    private IShip iShip;
    private Image background;
    private int weaponLevel;

    private Game() {
    }

    public static int getHEIGTH() {
        return HEIGTH;
    }

    //private Ranking ranking;

    public static int getWIDTH() {
        return WIDTH;
    }

    public static Game getGame() {
        if (game != null) {
            return game;
        } else {
            game = new Game();
            return game;
        }
    }

    public void addShip(IShip ship, double x, double y) {
        ship.getView().setTranslateX(x);
        ship.getView().setTranslateY(y);
        root.getChildren().add(ship.getView());
    }

    public void addBullet(IShip ship) {
//        ship.getBullet().getView().setTranslateX(ship.getView().getTranslateX());
//        ship.getBullet().getView().setTranslateY(ship.getView().getTranslateY());
//        root.getChildren().add(iShip.getBullet().getView());
    }

    public void addBullet(Node position) {
        root.getChildren().add(position);
    }

    public void onUpdate() {

        HashMap<Node, IBullet> bulletHashMapTMP = new HashMap<>();

        iShip.draw();
        for (Node node : bulletList.keySet()) {
            root.getChildren().remove(node);
            IBullet iBullet = bulletList.get(node);
            node = iBullet.draw(node);
            bulletHashMapTMP.put(node, iBullet);
        }
        bulletList = bulletHashMapTMP;
        for (Node node : bulletList.keySet()) {
            addBullet(node);
        }
    }

    private void checkKeyPressed() {
        window.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                iShip.moveLeft();
            } else if (e.getCode() == KeyCode.RIGHT) {
                iShip.moveRight();
            } else if (e.getCode() == KeyCode.SPACE) {
                System.out.println("space");
                Node startingPosition = iShip.shoot(weaponLevel).spawn();
                bulletList.put(startingPosition, iShip.shoot(weaponLevel));
            }
        });
    }

    public void startApp(Stage stage) {
        window = stage;
        //window.setResizable(false);
        window.setTitle("Statki");
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGTH);
        BackgroundImage myBI = new BackgroundImage(new Image("img/background.png", WIDTH, HEIGTH, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
        scene = new Scene(root);
        window.setScene(scene);

        iShip = Ship.getShipInstance();
        System.out.println("hp: " + iShip.getHP());
        addShip(iShip, Game.WIDTH / 2, Game.HEIGTH * 9 / 10);
//        addBullet(iShip);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        checkKeyPressed();

        window.show();


        Point2D p1 = new Point2D(2, 3);
        Point2D p2 = new Point2D(2, 2);
        Point2D p3 = new Point2D(2, 2);
        if (p1.equals(p2)) System.out.println("1=2");
        if (p1.equals(p3)) System.out.println("1=3");
        if (p2.equals(p3)) System.out.println("2=3");
    }



}
