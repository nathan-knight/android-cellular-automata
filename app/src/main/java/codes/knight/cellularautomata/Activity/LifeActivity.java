package codes.knight.cellularautomata.Activity;

/**
 * Created by thee-code-warrior on 9/22/2016.
 * Main activity for Cellular Automata
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import codes.knight.cellularautomata.View.LifeView;
import codes.knight.cellularautomata.R;

public class LifeActivity extends AppCompatActivity {

    private LifeView lifeView;
    final String LOG_TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_life);
        Log.d(LOG_TAG, "ContentView set");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
        lifeView = (LifeView) this.findViewById(R.id.lifeFieldView);
        if(lifeView != null) lifeView.onResume();
        else {
            Log.d(LOG_TAG, "lifeView is null?!");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
        if(lifeView != null) lifeView.onPause();
        else {
            Log.d(LOG_TAG, "lifeView is null?!");
        }
    }
}