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

    public void die()
    {
        new Explosion(world, this);
    }

    @Override
    public Rect getSource()
    {
        return enemy;
    }
}
