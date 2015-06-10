package cornelius.tessa.victor;

import android.graphics.Rect;

/**
 * Created by Cornelius on 5/21/2015.
 */
public class EnemyBlack extends Enemy
{
    final static Rect enemy = new Rect(192,90,213,104);
    final static int value = 500;
    public EnemyBlack(MyWorld theWorld)
    {
        super(theWorld);
    }

<<<<<<< HEAD
    public void die()
    {
        new Explosion(world, this);
        synchronized (world)
        {
            world.numKills++;
            world.score += value;
        }
    }

=======
>>>>>>> e0aa8d866b1a629e6435c0109a1784f5741fcc20
    @Override
    public Rect getSource()
    {
        return enemy;
    }
}
