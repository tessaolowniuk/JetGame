package cornelius.tessa.victor;

import android.graphics.Rect;

import edu.noctrl.craig.generic.GameObject;
import edu.noctrl.craig.generic.GameSprite;
import edu.noctrl.craig.generic.Point3F;

/**
 * Created by Vic on 6/2/2015.
 */
public class Explosion extends GameSprite
{
    protected static final Rect source = new Rect(329, 68, 358, 91);
    protected static final Point3F scale = Point3F.identity();

    public Explosion(MyWorld theWorld, GameObject victim)
    {
        super(theWorld);

        this.position.X = victim.position.X;
        this.position.Y = victim.position.Y;
        this.substance = Collision.None;

        theWorld.addObject(this);

        this.kill();
    }

    @Override
    public Rect getSource() {
        return source;
    }

    @Override
    public Point3F getScale() {
        return scale;
    }

    @Override
    public void cull()
    {

    }
}
