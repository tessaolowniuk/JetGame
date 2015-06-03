package cornelius.tessa.victor;

import android.content.Context;
import android.graphics.Rect;

import edu.noctrl.craig.generic.GameObject;
import edu.noctrl.craig.generic.GameSprite;
import edu.noctrl.craig.generic.Point3F;

/**
 * Created by Cornelius on 5/21/2015.
 */
public class EnemyBlack extends GameSprite
{
    final static Rect enemy = new Rect(192,90,213,104);
    final static Point3F scale = Point3F.identity();
    protected MyWorld world;

    protected Context context;

    public EnemyBlack(MyWorld theWorld)
    {
        super(theWorld);
        world = theWorld;
        this.position = new Point3F(1, 1, 0);
        this.context = world.context;

        //set up for collision
        this.substance = Collision.SolidAI;
        this.collidesWith = Collision.SolidPlayer;
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

    @Override
    public Point3F getScale()
    {
        return scale;
    }

    @Override
    public void cull()
    {

    }

    @Override
    public void collision(GameObject other)
    {

    }
}
