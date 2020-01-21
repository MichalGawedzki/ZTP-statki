import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class GunDecorator extends Bonus {

    private IBullet iBullet;
    private BulletFactory bulletFactory = new BulletFactory();
    private LocalTime timeLocal = LocalTime.now();
    private int time = 15000;
    private ImageView gunBonusImage;

    public GunDecorator(IShip iShip) throws IOException {
        super(iShip);
        addGunBonusLabel();
        iBullet = bulletFactory.getBullet(0);
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

    @Override
    public IBullet shoot() {
        return iBullet;
    }

    private void addGunBonusLabel() throws IOException {
        Image image = new Image("img/gun_label.png");
        gunBonusImage = new ImageView(image);
        gunBonusImage.setTranslateX(480);
        gunBonusImage.setTranslateY(25);
        gunBonusImage.setFitWidth(20);
        gunBonusImage.setFitHeight(20);
        Game.getGame().getRoot().getChildren().add(gunBonusImage);
    }

    @Override
    public IShip undecorate() throws IOException {
        Game.getGame().getRoot().getChildren().remove(gunBonusImage);
        return super.undecorate();
    }
}
