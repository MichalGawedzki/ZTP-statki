import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private Pane root;

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage stage) throws Exception {
        stage.show();
        System.out.println("halo");
        Game game = Game.getGame();
        game.startApp(stage);
    }



}
