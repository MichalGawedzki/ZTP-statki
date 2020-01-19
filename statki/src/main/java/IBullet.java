import javafx.scene.Node;

public interface IBullet {

//    Node getView();
//
//    void setView(Node node);

    Node draw(Node node);
    Node spawn();
    int getPower();
}
