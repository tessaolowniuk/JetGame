package cornelius.tessa.victor;

import android.content.Context;

import edu.noctrl.craig.generic.GameObject;
import edu.noctrl.craig.generic.GameSprite;
import edu.noctrl.craig.generic.Point3F;

/**
 * Created by Cornelius on 6/4/2015.
 */
public abstract class Enemy extends GameSprite
{
    protected final static Point3F scale = Point3F.identity();
    protected MyWorld world;
    protected Context context;
    protected int curStage;

    public Enemy(MyWorld theWorld)
    {
        super(theWorld);
        world = theWorld;
        this.position = new Point3F(1, 1, 0);
        this.context = world.context;
        //set up for collision
        this.substance = Collision.SolidAI;
        this.collidesWith = Collision.SolidPlayer;
    }

    public abstract void die();

    @Override
    public Point3F getScale()
    {
        return scale;
    }

    @Override
    public void cull()
    {
        this.kill();
        synchronized (world)
        {
            world.numKills++;
        }
    }

    @Override
    public void collision(GameObject other)
    {

    }
}
