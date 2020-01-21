import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ImmortalityDecorator extends Bonus {

    private LocalTime timeLocal = LocalTime.now();
    private int time = 7000;
    private ImageView immortalityBonusImage;

    public ImmortalityDecorator(IShip iShip) throws IOException {
        super(iShip);
        addImmortalityBonusLabel();
    }

    @Override
    public IBullet shoot() {

        return iShip.shoot();
    }

    @Override
    public int draw() {

        iShip.draw();
        if (ChronoUnit.MILLIS.between(timeLocal, LocalTime.now()) > time) {
            return 1;
        }
        return 0;

    }

    @Override
    public void setOnlyVelocity(int x, int y) {

    }

    private void addImmortalityBonusLabel() throws IOException {
        Image image = new Image("img/immortality_label.png");
        immortalityBonusImage = new ImageView(image);
        immortalityBonusImage.setTranslateX(480);
        immortalityBonusImage.setTranslateY(50);
        immortalityBonusImage.setFitWidth(20);
        immortalityBonusImage.setFitHeight(20);
        Game.getGame().getRoot().getChildren().add(immortalityBonusImage);
    }

    @Override
    public IShip undecorate() throws IOException {
        Game.getGame().getRoot().getChildren().remove(immortalityBonusImage);
        return super.undecorate();
    }
}
