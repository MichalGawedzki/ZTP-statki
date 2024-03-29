import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Game {

    private static final int HEIGTH = 500;
    private static final int WIDTH = 500;
    private static Stage window;
    private static Game game;

    static {
        try {
            game = new Game();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Scene scene;
    private Pane root;
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private ArrayList<BonusNode> bonusList = new ArrayList<BonusNode>();
    private HashMap<Node, IBullet> bulletList = new HashMap<Node, IBullet>();
    private HashMap<Node, IBullet> enemyBulletList = new HashMap<Node, IBullet>();
    private IShip iShip;
    private Label levelLabel = new Label();
    private Label scoreLabel = new Label();
    private Label hpLabel = new Label();
    private int score = 0;
    private int level = 1;
    private int spawnFrequency = 2500; // time between each spawn in miliseconds
    private LocalTime lastSpawnTime = LocalTime.now();
    private LocalTime timeLocal = LocalTime.now();
    private BulletFactory bulletFactory = new BulletFactory();
    private AnimationTimer timer;
    private Ranking ranking = new Ranking();
    private Label gameOverText;
    private TextField playerName = new TextField();
    private boolean scoreSaved;

    public Pane getRoot() {
        return root;
    }

    public BulletFactory getBulletFactory() {
        return bulletFactory;
    }

    private Game() throws IOException {
        lastSpawnTime = LocalTime.now();
    }

    public static int getHEIGTH() {
        return HEIGTH;
    }


    public static int getWIDTH() {
        return WIDTH;
    }

    public static Game getGame() throws IOException {
        if (game != null) {
            return game;
        } else {
            game = new Game();
            return game;
        }
    }

    public static boolean isTouchingBorder(Node node) {

        if (node.getTranslateX() <= 0 ||
                node.getTranslateX() >= WIDTH - node.getBoundsInParent().getWidth() ||
                node.getTranslateY() <= 0 || node.getTranslateY() >= HEIGTH - node.getBoundsInParent().getHeight()) {
            return true;
        }
        return false;
    }


    public void addShip(IShip ship, double x, double y) {
        ship.getView().setTranslateX(x);
        ship.getView().setTranslateY(y);
        root.getChildren().add(ship.getView());
    }


    private void addBullet(Node position) {
        root.getChildren().add(position);
    }

    private void addLabels() {

        levelLabel.setFont(new Font(16));
        levelLabel.setTextFill(Color.LIGHTGREY);
        levelLabel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        levelLabel.setOpacity(0.7);

        scoreLabel.setFont(new Font(16));
        scoreLabel.setTextFill(Color.LIGHTGREY);
        scoreLabel.setTranslateY(20);
        scoreLabel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        scoreLabel.setOpacity(0.7);

        hpLabel.setFont(new Font(16));
        hpLabel.setTextFill(Color.LIGHTGREY);
        hpLabel.setTranslateY(40);
        hpLabel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        hpLabel.setOpacity(0.7);

        root.getChildren().add(scoreLabel);
        root.getChildren().add(hpLabel);
    }

    private void gameOver() {

        timer.stop();
        gameOverText = new Label("GAME OVER\n\nLevel: " + level + "\nScore: " + score + "\n\nPress CTRL to play again\n\n" +
                "Press ENTER to save your score\nand see the Top 5 Ranking");
        playerName.setText("Player");
        gameOverText.setAlignment(Pos.CENTER);
        playerName.setAlignment(Pos.CENTER);

        root.getChildren().remove(levelLabel);
        root.getChildren().remove(scoreLabel);
        root.getChildren().remove(hpLabel);

        gameOverText.setFont(new Font(25));
        gameOverText.setTranslateX(50);
        gameOverText.setTranslateY(50);
        gameOverText.setOpacity(1);
        gameOverText.setTextFill(Color.BLACK);
        gameOverText.getStyleClass().add("outline");
        Color c = new Color(0, 0, 0, 0.55);
        gameOverText.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, new Insets(-100, -100, -100, -100))));

        playerName.setFont(new Font(22));
        playerName.setTranslateX(50);
        playerName.setTranslateY(420);
        playerName.setOpacity(1);
        playerName.setStyle("-fx-text-inner-color: green;");
        playerName.setVisible(true);

        root.getChildren().add(gameOverText);
        root.getChildren().add(playerName);

        onGameOverKeyPressed();

    }

    private void onGameOverKeyPressed() {
        window.getScene().setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.CONTROL) {
                window.setScene(new Scene(new Pane()));
                window.show();
                try {
                    startApp(window);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getCode() == KeyCode.ENTER) {
                if (!scoreSaved) {
                    ranking.addtoList(new RankPos(score, playerName.getText()));
                    scoreSaved = true;
                }
                gameOverText.setText(ranking.highScoreToText() + "Click CTRL to start new round :)");
                playerName.setVisible(false);
                try {
                    ranking.saveToFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });
    }

    private void addEnemy() throws IOException {

        if (ChronoUnit.MILLIS.between(lastSpawnTime, LocalTime.now()) > spawnFrequency) {
            Enemy enemy = new Enemy(level);
            enemyList.add(enemy);
            root.getChildren().addAll(enemy.getView(), new Text("1"));
            lastSpawnTime = LocalTime.now();
        }
    }

    private void updateLabels() {

        int hp = iShip.getHP() > 0 ? iShip.getHP() : 0;

        this.levelLabel.setText(" Level: " + level + " ");
        this.scoreLabel.setText(" Score: " + score + " ");
        this.hpLabel.setText(" HP: " + hp + " ");

        root.getChildren().remove(levelLabel);
        root.getChildren().remove(scoreLabel);
        root.getChildren().remove(hpLabel);
        root.getChildren().add(levelLabel);
        root.getChildren().add(scoreLabel);
        root.getChildren().add(hpLabel);

        if (iShip.getHP() <= 0) {
            gameOver();
        }
    }

    public void onUpdate() throws IOException {
        HashMap<Node, IBullet> bulletHashMapTMP = new HashMap<>();
        HashMap<Node, IBullet> bulletHashMapTMP2 = new HashMap<>();
        updateLabels();
        spawnBonus();

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

        for (Node node : enemyBulletList.keySet()) {
            root.getChildren().remove(node);
            IBullet iBullet = enemyBulletList.get(node);
            node = iBullet.draw(node);
            bulletHashMapTMP2.put(node, iBullet);
        }
        enemyBulletList = bulletHashMapTMP2;
        for (Node node : enemyBulletList.keySet()) {
            addBullet(node);
        }


        addEnemy();
        for (Enemy enemy : enemyList) {
            enemy.draw(root);
            if (ChronoUnit.MILLIS.between(enemy.getSpawnTime(), LocalTime.now()) > enemy.getShootFrequency()) {
                Node startingPosition = enemy.shoot().spawn(enemy.getView().getTranslateX(), enemy.getView().getTranslateY());
                enemyBulletList.put(startingPosition, enemy.shoot());
                enemy.setSpawnTime(LocalTime.now());
            }
        }
        checkCollisions();
        checkBulletBorder();

        if (iShip.draw() == 1) {
            iShip = iShip.undecorate();
        }

    }


    private void checkKeyPressed() {
        window.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                gameOver();
            } else if (e.getCode() == KeyCode.LEFT) {
                iShip.moveLeft();
            } else if (e.getCode() == KeyCode.RIGHT) {
                iShip.moveRight();
            } else if (e.getCode() == KeyCode.SPACE) {
                Node startingPosition = null;
                try {
                    startingPosition = iShip.shoot().spawn(iShip.getView().getTranslateX(), iShip.getView().getTranslateY());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                bulletList.put(startingPosition, iShip.shoot());
            }
        });
    }

    private void checkBulletBorder() {
        HashMap<Node, IBullet> bulletsTMP = new HashMap<>();
        HashMap<Node, IBullet> bulletsTMP1 = new HashMap<>();
        for (Node node : bulletList.keySet()) {
            if (node.getTranslateY() <= 0) {
                bulletsTMP.put(node, bulletList.get(node));
            }
        }
        for (Node node : enemyBulletList.keySet()) {
            if (node.getTranslateY() >= Game.getHEIGTH()) {
                bulletsTMP1.put(node, enemyBulletList.get(node));
            }
        }

        for (Node node : bulletsTMP.keySet()) {
            root.getChildren().remove(node);
            bulletList.remove(node);
        }

        for (Node node : bulletsTMP1.keySet()) {
            root.getChildren().remove(node);
            enemyBulletList.remove(node);
        }
    }

    private void checkCollisions() throws IOException {
        HashMap<Node, IBullet> bulletsTMP = new HashMap<>();
        ArrayList<Enemy> enemiesTMP = new ArrayList<>();
        ArrayList<BonusNode> bonusListTMP = new ArrayList<>();

        for (Node node : enemyBulletList.keySet()) {
            if (distanceBetween(node, iShip.getView()) < 10) {
                iShip.gotHit(enemyBulletList.get(node).getPower());

                bulletsTMP.put(node, enemyBulletList.get(node));
            }
        }

        for (Node node : bulletList.keySet()) {
            for (Enemy enemy : enemyList) {
                if (distanceBetween(node, enemy.getView()) < 10) {
                    enemy.gotHit();
                    if (!enemy.isAlive()) {
                        enemiesTMP.add(enemy);
                    }
                    bulletsTMP.put(node, bulletList.get(node));
                    score += iShip.getBullet().getPower();
                    levelUp();
                }
            }
            for (BonusNode bonusNode : bonusList) {

                if (distanceBetween(node, bonusNode.getView()) < 10) {
                    if (bonusNode instanceof HeartNode) {
                        iShip = new HeartDecorator(iShip);
                    } else if (bonusNode instanceof GunNode) {
                        iShip = new GunDecorator(iShip);
                    } else if (bonusNode instanceof ImmortalNode) {
                        iShip = new ImmortalityDecorator(iShip);
                    }
                    bonusListTMP.add(bonusNode);
                    bulletsTMP.put(node, bulletList.get(node));

                }

            }
        }


        for (Node node : bulletsTMP.keySet()) {
            if (bulletList.containsKey(node)) {
                bulletList.remove(node);
            } else
                enemyBulletList.remove(node);
            root.getChildren().remove(node);
        }
        for (Enemy enemy : enemiesTMP) {
            enemyList.remove(enemy);
            root.getChildren().remove(enemy.getView());
        }
        for (BonusNode bonusNode : bonusListTMP) {
            bonusList.remove(bonusNode);
            root.getChildren().remove(bonusNode.getView());
        }


    }

    private void levelUp() {

        if (score >= level * 25 && score <= level * 50 && level < 7) {
            level++;
            iShip.setWeaponLevel(level);
            spawnFrequency *= 0.85;
        }

    }

    private int distanceBetween(Node n1, Node n2) {
        int centerX1 = (int) (n1.getTranslateX() + n1.getBoundsInParent().getWidth()) / 2;
        int centerX2 = (int) (n2.getTranslateX() + n2.getBoundsInParent().getWidth()) / 2;
        int centerY1 = (int) (n1.getTranslateY() + n1.getBoundsInParent().getHeight()) / 2;
        int centerY2 = (int) (n2.getTranslateY() + n2.getBoundsInParent().getHeight()) / 2;

        int distance = (int) Math.sqrt(Math.pow(Math.abs(centerX1 - centerX2), 2) + Math.pow(Math.abs(centerY1 - centerY2), 2));
        return distance;
    }

    public void startApp(Stage stage) throws IOException {
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
        scene.getStylesheets().addAll(getClass().getResource(
                "outline.css"
        ).toExternalForm());
        window.setScene(scene);


        iShip = Ship.getShipInstance();
        resetData();
        System.out.println("hp: " + iShip.getHP());
        addLabels();
        updateLabels();
        addShip(iShip, Game.WIDTH / 2, Game.HEIGTH * 9 / 10);


        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    onUpdate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
        checkKeyPressed();
    }

    private void resetData() {
        level = 1;
        score = 0;
        iShip.setHP(50);
        iShip.setWeaponLevel(1);
        iShip.setOnlyVelocity(0, 0);
        enemyBulletList.clear();
        enemyList.clear();
        bulletFactory = new BulletFactory();
        bulletList.clear();
        spawnFrequency = 3000;
        scoreSaved = false;
    }


    public void spawnBonus() {
        Random rand = new Random();
        int randomNum = rand.nextInt((20 - 15) + 1) + 15;

        if (ChronoUnit.SECONDS.between(timeLocal, LocalTime.now()) > randomNum) {
            addBonus();
            timeLocal = LocalTime.now();
        }

    }

    public void addBonus() {
        Random rand = new Random();
        int randomBonus = rand.nextInt((3 - 1) + 1) + 1;
        System.out.println(randomBonus);
        if (randomBonus == 1) {
            HeartNode heartNode = new HeartNode();
            addBonusToMap(heartNode.getView());
            bonusList.add(heartNode);


        } else if (randomBonus == 2) {
            GunNode gunNode = new GunNode();
            addBonusToMap(gunNode.getView());
            bonusList.add(gunNode);

        } else {
            ImmortalNode immortalNode = new ImmortalNode();
            addBonusToMap(immortalNode.getView());
            bonusList.add(immortalNode);

        }
    }

    public void addBonusToMap(Node view) {
        root.getChildren().add(view);
    }


}
