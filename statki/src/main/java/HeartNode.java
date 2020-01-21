import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HeartNode extends  BonusNode{



    public HeartNode()
    {

        System.out.println("new HeartNode");
        Image image = new Image("img/heart.png");
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
