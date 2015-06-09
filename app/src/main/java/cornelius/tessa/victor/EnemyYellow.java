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
