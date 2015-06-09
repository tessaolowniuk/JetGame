// CannonGame.java
// MainActivity displays the JetGameFragment
package com.deitel.cannongame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cornelius.tessa.victor.MyWorld;

public class MainActivity extends Activity {
    MyWorld myWorld;

    // called when the app first launches
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call super's onCreate method
        setContentView(R.layout.activity_main); // inflate the layout
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (item.getItemId()) {
            case R.id.stageSelection:
                myWorld.stageSelection();
                return true;

            case R.id.highScores:
                myWorld.retrieveHighScores();
                return true;

            case R.id.about:
                myWorld.about();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


} // end class MainActivity

/*********************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and * Pearson Education, *
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this   *
 * book have used their * best efforts in preparing the book. These efforts      *
 * include the * development, research, and testing of the theories and programs *
 * * to determine their effectiveness. The authors and publisher make * no       *
 * warranty of any kind, expressed or implied, with regard to these * programs   *
 * or to the documentation contained in these books. The authors * and publisher *
 * shall not be liable in any event for incidental or * consequential damages in *
 * connection with, or arising out of, the * furnishing, performance, or use of  *
 * these programs.                                                               *
 *********************************************************************************/
