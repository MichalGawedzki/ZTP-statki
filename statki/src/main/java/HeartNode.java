import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class HeartNode extends BonusNode {


    public HeartNode() {

        Image image = new Image("img/heart.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        view = imageView;

        view.setTranslateX(Math.random() * (Game.getWIDTH() - view.getBoundsInParent().getWidth()));
        view.setTranslateY((Math.random() * (Game.getHEIGTH() - view.getBoundsInParent().getHeight()) + 50) * 0.8);
    }

    public Node getView() {
        return view;
    }

}
