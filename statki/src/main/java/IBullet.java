import javafx.scene.Node;

import java.io.IOException;

public interface IBullet {

//    Node getView();

//    void setView(Node node);

    Node draw(Node node);
    Node spawn(double xx, double yy) throws IOException;
    int getPower();
}
