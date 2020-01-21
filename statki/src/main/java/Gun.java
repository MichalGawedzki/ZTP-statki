import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Gun extends Bonus {

    private IBullet iBullet;
    private BulletFactory bulletFactory = new BulletFactory();
    private LocalTime timeLocal = LocalTime.now();
    private int time = 5000;

    public Gun(IShip iShip)
    {
        super(iShip);
        iBullet=bulletFactory.getBullet(0);

    }


    @Override
    public void draw() {
        iShip.draw();
        //todo
//        if(ChronoUnit.MILLIS.between(timeLocal, LocalTime.now()) > time)
//        {
//
//        }


    }

    @Override
    public void setOnlyVelocity(int x, int y) {

    }

    @Override
    public IBullet shoot() {
        return iBullet;
    }



}
