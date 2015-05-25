package cornelius.tessa.victor;

import android.graphics.Rect;

import edu.noctrl.craig.generic.GameSprite;
import edu.noctrl.craig.generic.Point3F;
import edu.noctrl.craig.generic.World;

public class MyShip extends GameSprite {

    protected static final Rect source = new Rect(64, 14, 128, 50);
    protected static final Point3F scale = Point3F.identity();

    public MyShip(World theWorld) {
        super(theWorld);
        this.speed = 0;
        this.baseVelocity = new Point3F(1,1,0);
        this.updateVelocity();
    }

    public void speedUp() {
        this.speed = this.speed + 50;
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

