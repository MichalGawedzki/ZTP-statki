import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.text.Position;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private static final int HEIGTH = 500;
    private static final int WIDTH = 500;
    private static Stage window;
    private static Game game = new Game();
    private Scene scene;
    private Pane root;
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private ArrayList<Bonus> bonusList = new ArrayList<Bonus>();
    private HashMap<Position, Bullet> bulletList = new HashMap<Position, Bullet>();
    private IShip ship;
    private Image background;

    //private Ranking ranking;

    private Game() {
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

    public void onUpdate() {
        ship.draw();
    }

    public void startApp(Stage stage) {
        window = stage;
        window.setResizable(false);
        window.setTitle("Statki");
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGTH);
        BackgroundImage myBI = new BackgroundImage(new Image("img/background.png", WIDTH, HEIGTH, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
        scene = new Scene(root);
        window.setScene(scene);

        ship = Ship.getShipInstance();
        ship.setVelocity(1.0, 0.0);
        ship.shoot();
        addShip(ship, Game.WIDTH / 2, Game.HEIGTH * 9 / 10);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();


        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                ship.setVelocity(-1, 0);
            } else if (e.getCode() == KeyCode.RIGHT) {
                ship.setVelocity(1, 0);

            }});

            window.show();
        }


    }
