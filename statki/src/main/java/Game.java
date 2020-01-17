import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private static Stage window;
    private Scene scene;
    private static final int HEIGTH = 500;
    private static final int WIDTH = 500;
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private ArrayList<Bonus> bonusList = new ArrayList<Bonus>();
    private HashMap<Position, Bullet> bulletList = new HashMap<Position, Bullet>();
    private IShip ship;
    private static Game game = new Game();
    private Image background;

    //private Ranking ranking;

    private Game(){

    }

    public void startApp(Stage stage){
        window = stage;
        window.setResizable(false);
        window.setTitle("Statki");
        window.setHeight(HEIGTH);
        window.setWidth(WIDTH);
        //window.initModality(Modality.APPLICATION_MODAL);
        window.show();
    }

    public static Game getGame(){
        return game;
    }


}
