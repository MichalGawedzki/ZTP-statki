import java.util.HashMap;

public class BulletFactory {

    private HashMap<Integer, IBullet> bullets = new HashMap<>();

    public IBullet getBullet(int idx) {
        if (!bullets.containsKey(idx))
            bullets.put(idx, new Bullet(idx));
        return bullets.get(idx);
    }
}
