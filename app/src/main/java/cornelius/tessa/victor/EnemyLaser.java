package cornelius.tessa.victor;

import android.graphics.Rect;

import edu.noctrl.craig.generic.GameObject;
import edu.noctrl.craig.generic.GameSprite;
import edu.noctrl.craig.generic.Point3F;

/**
 * Created by Vic on 6/2/2015.
 */
public class EnemyLaser extends GameSprite {

    protected static final Rect source = new Rect(256, 80, 272, 89);
    protected static final Point3F scale = Point3F.identity();

    public EnemyLaser(MyWorld theWorld)
    {
        super(theWorld);
        this.speed = 0;
        this.baseVelocity = new Point3F(1,1,0);
        this.updateVelocity();

        //set up for collision
        this.substance = Collision.Solid;
        this.collidesWith = Collision.SolidAI;
    }

    public void speedUp() {
        this.speed = this.speed + 1500;
    }

    public void fire(Point3F touch)
    {
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

    @Override
    public void collision(GameObject other)
    {
        MyShip ship = (MyShip) other;

    }
}
