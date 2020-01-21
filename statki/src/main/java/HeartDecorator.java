import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class HeartDecorator extends Bonus {


    private int addhp = 5;
    private LocalTime time = LocalTime.now();
    private int spawnFrequency = 1000;
    private int duration = 5;
    private LocalTime spawnTime;
    private ImageView heartBonusImage;

    public HeartDecorator(IShip iShip) throws IOException {
        super(iShip);
        addHeartBonusLabel();
        spawnTime = LocalTime.now();
    }

    @Override
    public IBullet shoot() {

        return iShip.shoot();
    }

    @Override
    public int draw() {

        iShip.draw();
        int currentHP = iShip.getHP();
        if ((ChronoUnit.MILLIS.between(time, LocalTime.now()) > spawnFrequency) && (currentHP <= 50)) {
            iShip.setHP(currentHP + addhp);
            time = LocalTime.now();


        }
        if (ChronoUnit.SECONDS.between(spawnTime, LocalTime.now()) > duration) {
            return 1;
        }
        return 0;


    }

    @Override
    public void setOnlyVelocity(int x, int y) {

    }

    @Override
    public void gotHit(int power) {
        super.gotHit(power);
    }

    private void addHeartBonusLabel() throws IOException {
        Image image = new Image("img/heart_label.png");
        heartBonusImage = new ImageView(image);
        heartBonusImage.setTranslateX(480);
        heartBonusImage.setFitWidth(20);
        heartBonusImage.setFitHeight(20);
        Game.getGame().getRoot().getChildren().add(heartBonusImage);
    }

    @Override
    public IShip undecorate() throws IOException {
        Game.getGame().getRoot().getChildren().remove(heartBonusImage);
        return super.undecorate();
    }
}
