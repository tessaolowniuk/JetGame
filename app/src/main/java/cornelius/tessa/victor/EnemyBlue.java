package cornelius.tessa.victor;

import android.graphics.Rect;

/**
 * Created by Cornelius on 5/21/2015.
 */
public class EnemyBlue extends Enemy
{
    final static Rect enemy = new Rect(192,65,213,79);
    final static int value = 800;
    public EnemyBlue(MyWorld theWorld)
    {
        super(theWorld);

    }

    public void die() {new Explosion(world, this);}

    public int killScore()
    {
<<<<<<< HEAD
        return value;
=======
        new Explosion(world, this);
        synchronized (world)
        {
            world.numKills++;
        }
>>>>>>> 649aa280962d7fb5c2ccfc7fa2abb6a0afa6e419
    }
    @Override
    public Rect getSource()
    {
        return enemy;
    }
}
