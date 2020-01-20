import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Immortality extends Bonus {

    private LocalTime time = LocalTime.now();
    private int spawnFrequency = 7000;

    public Immortality(IShip iShip)
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

    }

    //todo
}
