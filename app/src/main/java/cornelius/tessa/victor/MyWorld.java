package cornelius.tessa.victor;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

import edu.noctrl.craig.generic.GameSprite;
import edu.noctrl.craig.generic.Point3F;
import edu.noctrl.craig.generic.SoundManager;
import edu.noctrl.craig.generic.World;

/**
 * Created by Cornelius on 5/21/2015.
 */
public class MyWorld extends World
{
    private GameSprite enemy;
    private Random rand = new Random();
    private int stage;
    protected MyShip ship;

    public MyWorld(StateListener listener, SoundManager sounds)
    {
        super(listener, sounds);
        stage = 1;
        ship = new MyShip(this);
        ship.position.X = 128;
        ship.position.Y += 765 / 2;
        this.addObject(ship);

        // Populates the screen with ten enemies in random positions on a separate thread
        // Enemies spawn 300px from the left edge of the screen
        // TODO: change spawn location based off of right edge of player ship
        new Thread(new Runnable()
        {
            public void run()
            {
                for(int i = 0; i < 10; i++)
                {
                    switch(rand.nextInt(3))
                    {
                        case 0:
                            enemy = new Enemy(MyWorld.this);
                            break;
                        case 1:
                            enemy = new EnemyBlack(MyWorld.this);
                            break;
                        default:
                            enemy = new EnemyYellow(MyWorld.this);
                    }

                    Log.i("enemy spawn", "value world width is " + width);
                    Log.i("enemy spawn", "value world height is " + height);
                    enemy.position.X = width * rand.nextFloat();
                    while(enemy.position.X < 300) enemy.position.X = width * rand.nextFloat();
                    enemy.position.Y = height * rand.nextFloat();
                    addObject(enemy);
                }
            }
        }).start();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN && stage == 1)
        {

        }
        //ship is allowed to move in stage 2
        else if(event.getActionMasked() == MotionEvent.ACTION_DOWN && stage == 2)
        {
            Point3F touch = new Point3F(event.getX(), event.getY(), 0);
            Point3F currentVelocity = ship.baseVelocity.clone();
            currentVelocity = touch.subtract(ship.position).normalize();
            ship.baseVelocity = currentVelocity;
            ship.speedUp();
            ship.updateVelocity();
        }

        return true;
    }
}
