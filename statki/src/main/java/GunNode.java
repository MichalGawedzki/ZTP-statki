import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GunNode extends BonusNode {


    public GunNode()
    {

        System.out.println("new GunNode");
        Image image = new Image("img/gun.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        view=imageView;

        view.setTranslateX(Math.random() * (Game.getWIDTH() - view.getBoundsInParent().getWidth()));
        view.setTranslateY(Math.random() * (Game.getHEIGTH() - view.getBoundsInParent().getHeight()));
    }

    public Node getView() {
        return view;
    }


}
