import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GunNode extends BonusNode {


    public GunNode() {
        Image image = new Image("img/gun.png");
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
