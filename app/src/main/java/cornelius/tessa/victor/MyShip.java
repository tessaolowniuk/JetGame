package cornelius.tessa.victor;

import android.content.Context;
import android.graphics.Rect;

import edu.noctrl.craig.generic.GameObject;
import edu.noctrl.craig.generic.GameSprite;
import edu.noctrl.craig.generic.Point3F;

public class MyShip extends GameSprite {

    protected static final Rect source = new Rect(64, 14, 128, 50);
    protected static final Point3F scale = Point3F.identity();
    protected Context context;

    public MyShip(MyWorld theWorld) {
        super(theWorld);
        this.speed = 0;
        this.baseVelocity = new Point3F(1,1,0);
        this.updateVelocity();
        this.context = theWorld.context;
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

    @Override
    public void collision(GameObject other)
    {
    }
}

