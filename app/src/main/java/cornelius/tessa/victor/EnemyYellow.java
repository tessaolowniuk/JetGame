package cornelius.tessa.victor;

import android.graphics.Rect;

/**
 * Created by Cornelius on 5/21/2015.
 */
public class EnemyYellow extends Enemy
{
    final static Rect enemy = new Rect(150,90,171,104);
    final static int value = 1000;
    public EnemyYellow(MyWorld theWorld)
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
