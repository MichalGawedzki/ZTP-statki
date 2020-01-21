import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ImmortalityDecorator extends Bonus {

    private LocalTime timeLocal = LocalTime.now();
    private int time = 7000;


    public ImmortalityDecorator(IShip iShip)
    {
        super(iShip);

    }

    @Override
    public IBullet shoot() {

        return iShip.shoot();
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

    //todo
}
