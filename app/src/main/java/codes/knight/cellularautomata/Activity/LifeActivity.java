package codes.knight.cellularautomata.Activity;

/**
 * Created by thee-code-warrior on 9/22/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import codes.knight.cellularautomata.LifeView;
import codes.knight.cellularautomata.R;

public class LifeActivity extends AppCompatActivity {

    private LifeView surfaceView;
    private Thread gameThread;

    private boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        surfaceView = new LifeView(this);
        setContentView(R.layout.activity_life);

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