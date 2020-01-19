import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
    private Label scoreLabel = new Label();
    private Label hpLabel = new Label();
    private int score = 0;
    private Image background;
    private int weaponLevel = 2;
    private long spawnFrequency = 3000; // time between each spawn in miliseconds
    private LocalTime lastSpawnTime;

    private Game() {
        lastSpawnTime = LocalTime.now();
        System.out.println(lastSpawnTime);
        System.out.println(ChronoUnit.MILLIS.between(lastSpawnTime, lastSpawnTime.minusSeconds(2)));
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

    public void addLabels() {
        scoreLabel.setFont(new Font(16));
        scoreLabel.setTextFill(Color.LIGHTGREY);
        scoreLabel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        scoreLabel.setOpacity(0.7);

        hpLabel.setFont(new Font(16));
        hpLabel.setTextFill(Color.LIGHTGREY);
        hpLabel.setTranslateY(20);
        hpLabel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        hpLabel.setOpacity(0.7);

        root.getChildren().add(scoreLabel);
        root.getChildren().add(hpLabel);
    }

    public void updateLabels() {
        this.scoreLabel.setText(" Score: " + score + " ");
        this.hpLabel.setText(" HP: " + iShip.getHP() + " ");

        root.getChildren().remove(scoreLabel);
        root.getChildren().remove(hpLabel);
        root.getChildren().add(scoreLabel);
        root.getChildren().add(hpLabel);
    }

    public void onUpdate() {
        HashMap<Node, IBullet> bulletHashMapTMP = new HashMap<>();
        updateLabels();
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
        checkCollisions();
    }

    private void checkKeyPressed() {
        window.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                iShip.moveLeft();
            } else if (e.getCode() == KeyCode.RIGHT) {
                iShip.moveRight();
            } else if (e.getCode() == KeyCode.SPACE) {
                Node startingPosition = iShip.shoot(weaponLevel).spawn();
                bulletList.put(startingPosition, iShip.shoot(weaponLevel));
            }
        });
    }

    private void checkCollisions() {
//        System.out.println(distanceBetween(iShip.getView(), enemyList.get(0).getView()));
        HashMap<Node, IBullet> bulletsTMP = new HashMap<>();

        for (Node node : bulletList.keySet()) {
            for (Enemy enemy : enemyList) {
                if (distanceBetween(node, enemy.getView()) < 10) {
                    System.out.println("kolizja");
                    bulletsTMP.put(node, bulletList.get(node));
                    score++;
                }
//                if(isCollisionBetween(node, enemy.getView())){
//                    System.out.println("kolizja");
//                    bulletsTMP.put(node, bulletList.get(node));
//                    score++;
//                }
            }
        }
        for (Node node : bulletsTMP.keySet()) {
            bulletList.remove(node);
            root.getChildren().remove(node);
        }


    }

    private int distanceBetween(Node n1, Node n2) {
        int centerX1 = (int) (n1.getTranslateX() + n1.getBoundsInParent().getWidth()) / 2;
        int centerX2 = (int) (n2.getTranslateX() + n2.getBoundsInParent().getWidth()) / 2;
        int centerY1 = (int) (n1.getTranslateY() + n1.getBoundsInParent().getHeight()) / 2;
        int centerY2 = (int) (n2.getTranslateY() + n2.getBoundsInParent().getHeight()) / 2;
//        System.out.println("centerX1 " + centerX1);
//        System.out.println("centerX2 " + centerX2);
//        System.out.println("centerY1 " + centerY1);
//        System.out.println("centerY2 " + centerY2);

        int distance = (int) Math.sqrt(Math.pow(Math.abs(centerX1 - centerX2), 2) + Math.pow(Math.abs(centerY1 - centerY2), 2));
        return distance;
    }

    private boolean isCollisionBetween(Node n1, Node n2) {
        double centerX1 =  (n1.getTranslateX() + n1.getBoundsInParent().getWidth()) / 2;
        double centerX2 =  (n2.getTranslateX() + n2.getBoundsInParent().getWidth()) / 2;
        double centerY1 =  (n1.getTranslateY() + n1.getBoundsInParent().getHeight()) / 2;
        double centerY2 =  (n2.getTranslateY() + n2.getBoundsInParent().getHeight()) / 2;

        if (Math.abs(centerX1 - centerX2) < ((n1.getBoundsInParent().getWidth()) / 2 + (n2.getBoundsInParent().getWidth()) / 2) &&
                Math.abs(centerY1 - centerY2) < ((n1.getBoundsInParent().getHeight()) / 2 + (n2.getBoundsInParent().getHeight()) / 2)) {
            System.out.println("kolizja");
            return true;
        }
        return false;
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
        addLabels();
        updateLabels();
        addShip(iShip, Game.WIDTH / 2, Game.HEIGTH * 9 / 10);


        Enemy enemy = new Enemy();
        enemy.draw(root);
        enemyList.add(enemy);
        Enemy enemy2 = new Enemy();
        enemy2.draw(root);
        enemyList.add(enemy2);
        Enemy enemy3 = new Enemy();
        enemy3.draw(root);
        enemyList.add(enemy3);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();
        checkKeyPressed();
        window.show();
    }

}
