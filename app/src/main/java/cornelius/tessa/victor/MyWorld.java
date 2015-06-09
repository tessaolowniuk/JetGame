package cornelius.tessa.victor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.deitel.cannongame.R;

import java.util.ArrayList;
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
    public int score;
    private ArrayList<Integer> highScores;
    protected MyShip ship;
    protected Context context;
    final int HIGH_SCORE_MAX = 5;

    // Motion Variables
    // The ‘active pointer’ is the one currently moving our object.
    private int mActivePointerId;
    float mLastTouchY;

    final int MAX_LIST_SIZE = 5;
    final String PREF_NAME = "JetGame Scores";

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public MyWorld(StateListener listener, SoundManager sounds, Context context)
    {
        super(listener, sounds);
        this.context = context;
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        highScores = new ArrayList<>();

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
        retrieveHighScores();

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
                            enemy = new EnemyBlue(MyWorld.this);
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

    public void updateHighScores()
    {
        if(highScores.size() == 0)
            highScores.add(score);
        else
        {
            Boolean openSpaces = highScores.size() < HIGH_SCORE_MAX ? true : false;

            for(int highScore : highScores)
            {
                if(score > highScore)
                {
                    int pos = highScores.indexOf(highScore);
                    highScores.add(pos, score);
                    break;
                }
            }

            // current score may not be the highest
            if(openSpaces)
                highScores.add(score);
        }

        //clear out extras
        if(highScores.size() >= HIGH_SCORE_MAX)
            for(int i = highScores.size() - 1; highScores.size() != HIGH_SCORE_MAX; i--)
                highScores.remove(i);
    }

    public void storeHighScores()
    {
        if(highScores.size() >= 1)
        {
            String highScoreString = "";
            for(int highScore : highScores)
                highScoreString += Integer.toString(highScore).trim() + " ";
            editor.putString("highScores", highScoreString);
            editor.commit();
        }
    }

    public void retrieveHighScores()
    {
        String highScoreString = sharedPref.getString("highScores","");
        if(!highScoreString.isEmpty())
        {
            String[] highScoreStringArray = highScoreString.split(" ");
            for(int i = 0; i < highScoreStringArray.length; i++)
                highScores.add(Integer.parseInt(highScoreStringArray[i].trim()));
        }
    }
    //Calculates the player's score
    public void calculateScore()
    {
        enemyKill ++;

        //if()
       // {

       // }
    }

    //Displays about message
    public void about()
    {
        new AlertDialog.Builder(context)
                .setTitle("About")
                .setMessage("This game was created by Cornelius, Tessa, & Victor. Homage to Brain Craig, for game engine")
                .setNeutralButton("Ok", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
    //Allows user to select which stage they would like to play
    public void stageSelection()
    {
        new AlertDialog.Builder(context)
                .setTitle("Stage Selection")
                .setMessage("Choose the stage you would like to play:")
                .setNeutralButton("Stage 1", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("Stage 2", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                })
                .setNeutralButton("Stage 3", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
