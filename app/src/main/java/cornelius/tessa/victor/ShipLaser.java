package cornelius.tessa.victor;

import android.graphics.Rect;

import edu.noctrl.craig.generic.GameSprite;
import edu.noctrl.craig.generic.Point3F;
import edu.noctrl.craig.generic.World;

/**
 * Created by Vic on 5/30/2015.
 */
public class ShipLaser extends GameSprite {

    protected static final Rect source = new Rect(256, 69, 272, 76);
    protected static final Point3F scale = Point3F.identity();

    public ShipLaser(World theWorld)
    {
        super(theWorld);
        this.speed = 0;
        this.baseVelocity = new Point3F(1,1,0);
        this.updateVelocity();

    }

    public void speedUp() {
        this.speed = this.speed + 350;
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
    public void cull() {

    }
}
