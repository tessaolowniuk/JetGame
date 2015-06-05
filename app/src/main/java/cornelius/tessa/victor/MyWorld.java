package cornelius.tessa.victor;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

import com.deitel.cannongame.R;

import java.util.Random;

import edu.noctrl.craig.generic.GameSprite;
import edu.noctrl.craig.generic.Point3F;
import edu.noctrl.craig.generic.SoundManager;
import edu.noctrl.craig.generic.World;

/**
 * Created by Cornelius on 5/21/2015.
 */

public class MyWorld extends World implements MediaPlayer.OnCompletionListener
{
    private GameSprite enemy;
    private Random rand = new Random();
    private int stage;
    protected MyShip ship;

    protected Context context;

    // Motion Variables
    // The ‘active pointer’ is the one currently moving our object.
    private int mActivePointerId;
    float mLastTouchY;

    public MyWorld(StateListener listener, SoundManager sounds, Context context)
    {
        super(listener, sounds);
        this.context = context;

        // Sound initialization
        MediaPlayer mediaPlayer = MediaPlayer.create(this.context, R.raw.game_music);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.start(); // no need to call prepare() here because create() does that for you

        // Enivronment initialization
        stage = 2;  // starting at stage 2 to test movement code
        ship = new MyShip(this);
        ship.position.X = 128;
        ship.position.Y += 765 / 2;
        mLastTouchY = ship.position.Y;
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
        ShipLaser shipLaser = new ShipLaser(this);
        shipLaser.position.Y = ship.position.Y;
        shipLaser.position.X = ship.position.X;
        Point3F touch = new Point3F(event.getX(), event.getY(), 0);
        
        switch(event.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                if(stage == 1)
                {
                    // have to add object in switch case in order to avoid animation bug
                    this.addObject(shipLaser);
                    shipLaser.fireAtPos(touch);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if(stage == 2)
                {
                    // Find the index of the active pointer and fetch its position
                    final int pointerIndex = event.findPointerIndex(mActivePointerId);
                    final float y = event.getY(pointerIndex);
                    // Calculate the distance moved
                    final float dy = y - mLastTouchY;
                    // Move the object
                    ship.position.Y += dy;
                    // Remember this touch position for the next move event
                    mLastTouchY = y;
                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                if(stage == 2)
                {
                    // have to add object in switch case in order to avoid animation bug
                    this.addObject(shipLaser);
                    shipLaser.fire(touch);
                }
                break;
        }

        return true;
    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {
        try
        {
            mp.start();
        }
        catch(Exception e) { e.printStackTrace(); }
    }
}