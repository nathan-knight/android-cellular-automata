package codes.knight.cellularautomata.Activity;

/**
 * Created by thee-code-warrior on 9/22/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import codes.knight.cellularautomata.LifeView;

public class LifeActivity extends AppCompatActivity {

    private LifeView surfaceView;
    private Thread gameThread;

    private boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_life);
        setContentView(new LifeView(this));

//        surfaceView = (LifeView) findViewById(R.id.surfaceView);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}