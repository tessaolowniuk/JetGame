package cornelius.tessa.victor;

import android.graphics.Rect;

import edu.noctrl.craig.generic.GameSprite;
import edu.noctrl.craig.generic.Point3F;

/**
 * Created by Cornelius on 5/21/2015.
 */
public class Enemy extends GameSprite
{
    final static Rect enemy = new Rect(192,65,213,79);
    final static Point3F scale = Point3F.identity();

    public Enemy(MyWorld theWorld)
    {
        super(theWorld);
        this.position = new Point3F(1, 1, 0);
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
}