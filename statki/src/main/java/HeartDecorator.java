import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class HeartDecorator extends Bonus {


    private int addhp = 5;
    private LocalTime time = LocalTime.now();
    private int spawnFrequency = 1000;
    private int duration = 5;
    private LocalTime spawnTime;

    public HeartDecorator(IShip iShip)
    {
        super(iShip);
        System.out.println("got Heart");
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
        if((ChronoUnit.MILLIS.between(time, LocalTime.now()) > spawnFrequency) && (currentHP<=50))
        {
            iShip.setHP(currentHP+addhp);
            time = LocalTime.now();


        }
        if (ChronoUnit.SECONDS.between(spawnTime, LocalTime.now())>duration) {
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
}
