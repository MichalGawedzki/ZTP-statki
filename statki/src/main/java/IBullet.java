import javafx.scene.Node;

public interface IBullet {

    Node getView();
    void setView(Node node);
    void draw();
}
