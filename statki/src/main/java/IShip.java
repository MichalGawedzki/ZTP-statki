import javafx.scene.Node;
import javafx.scene.image.Image;

public interface IShip {

    int HP = 100;
    int dx = 10, dy = 10;
    Bullet bullet = new Bullet();
    Ship shipInstance = Ship.getShipInstance();
    Image image = new Image("/resources/ship/png");

    public void move();
    public void shoot();
    public void spawn();
    public void getInstance();
    public void draw();
    public Node getView();
    public void setVelocity(double x, double y);
}
