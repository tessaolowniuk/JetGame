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

<<<<<<< HEAD
    public void die()
    {
        new Explosion(world, this);
        synchronized (world)
        {
            world.numKills++;
            world.score += value;
        }
=======
    public int killScore()
    {
        return value;
>>>>>>> e0aa8d866b1a629e6435c0109a1784f5741fcc20
    }

    @Override
    public Rect getSource()
    {
        return enemy;
    }
}
