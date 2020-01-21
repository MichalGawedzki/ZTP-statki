import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Heart extends Bonus {


    private int addhp = 10;
    private LocalTime time = LocalTime.now();
    private int spawnFrequency = 1000;
    public Heart(IShip iShip)
    {
        super(iShip);

    }

    @Override
    public IBullet shoot() {

        return iShip.shoot();
    }

    @Override
    public void draw() {

        iShip.draw();
        int currentHP = iShip.getHP();
        if((ChronoUnit.MILLIS.between(time, LocalTime.now()) > spawnFrequency) && (currentHP<=50))
        {
            iShip.setHP(currentHP+addhp);
            time = LocalTime.now();
        }


    }

    @Override
    public void setOnlyVelocity(int x, int y) {

    }


}
