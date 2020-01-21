import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class GunDecorator extends Bonus {

    private IBullet iBullet;
    private BulletFactory bulletFactory = new BulletFactory();
    private LocalTime timeLocal = LocalTime.now();
    private int time = 5000;

    public GunDecorator(IShip iShip)
    {
        super(iShip);

        iBullet=bulletFactory.getBullet(0);
    }


    @Override
    public int draw() {
        iShip.draw();

        if(ChronoUnit.MILLIS.between(timeLocal, LocalTime.now()) > time)
        {
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



}
