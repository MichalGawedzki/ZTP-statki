import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet implements IBullet {

    int index;

    public int getPower() {
        return power;
    }

    int power;
    int velocity;
//    private Node view;
    Color color;
    private String pathToImg;
    Image image;


    Bullet(int index) {
        this.index = index;
//        view = new Circle(5, 5, 5, Color.RED);
        if (index == -3) {
            power = 5;
            velocity = 3;
            color = Color.PURPLE;
            pathToImg = "img/bullet_enemy.png";
            System.out.println("new Bullet object PURPLE");
        } else
        if (index == -2) {
            power = 4;
            velocity = 3;
            color = Color.PURPLE;
            pathToImg = "img/bullet_enemy.png";
            System.out.println("new Bullet object PURPLE");
        } else
        if (index == -1) {
            power = 3;
            velocity = 3;
            color = Color.PURPLE;
            pathToImg = "img/bullet_enemy.png";
            System.out.println("new Bullet object PURPLE");
        } else
        if (index == 0) {
            power = 20;
            velocity = -5;
            color = Color.PURPLE;
            pathToImg = "img/bullet_enemy.png";
            System.out.println("new SUPA BULLET");
        } else if (index == 1) {
            power = 1;
            velocity = -1;
            color = Color.GREEN;
            pathToImg = "img/bullet_yellow.png";
            System.out.println("new Bullet object GREEN");
        } else if (index == 2) {
            power = 2;
            velocity = -2;
            color = Color.CORAL;
            pathToImg = "img/bullet_yellow.png";
            System.out.println("new Bullet object CORAL");
        } else if (index == 3){
            power = 3;
            velocity = -3;
            color = Color.RED;
            pathToImg = "img/bullet_yellow.png";
            System.out.println("new Bullet object RED");
        }
        image = new Image(pathToImg);
    }


    @Override
    public Node draw(Node view) {

//        Image image = new Image(pathToImg);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        if(index == 0){
            imageView.setRotate(135);
        }
        Node newNode = imageView;

//        Node newNode = new Circle(5, 5, 5, color);
        newNode.setTranslateY(view.getTranslateY() + velocity);
        newNode.setTranslateX(view.getTranslateX());

        return newNode;
    }

    @Override
    public Node spawn(double xx, double yy) {
//        double xx = iShip.getView().getTranslateX();
//        double yy = iShip.getView().getTranslateY();
//        Node startingPosition = new Circle(5, 5, 5, Color.RED);
//        startingPosition.setTranslateX(xx);
//        startingPosition.setTranslateY(yy);
//        bulletList.put(startingPosition, iShip.shoot(weaponLevel));
//        addBullet(startingPosition);
////                addBullet(iShip);
//        System.out.println(bulletList);


//        double xx = IShip.getShipInstance().getView().getTranslateX();
//        double yy = IShip.getShipInstance().getView().getTranslateY();
        Node view = new Circle(5, 5, 5, Color.RED);
        System.out.println(IShip.getShipInstance().getView().getBoundsInParent().getWidth());
        view.setTranslateX(xx+IShip.getShipInstance().getView().getBoundsInParent().getWidth()/4);
        view.setTranslateY(yy);
        return view;



//        view.setTranslateX(view.getTranslateX() + velocity.getX());
//        if (isTouchingLeftBorder() || isTouchingRightBorder()) {
//            velocity = new Point2D(0, 0);
//        }
    }
}
