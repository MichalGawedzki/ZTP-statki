import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Enemy{

    private int HP;
    private int frequency;
    private IBullet iBullet;
    private Strategy strategy;
    private Image image;
    private Node view;
    private IShip playerShip;

    public Enemy(){
        HP = 5;
        this.view = new Rectangle(20, 40, Color.VIOLET);
        view.setTranslateY(10);
        view.setTranslateX(Math.random()*Game.getWIDTH());
//        view.setTranslateX(11);
    }

    public void draw(Pane root){
        root.getChildren().remove(view);
        root.getChildren().add(view);
    }

    public Node getView() {
        return view;
    }
}
