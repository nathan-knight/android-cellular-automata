package codes.knight.cellularautomata;

/**
 * Created by thee-code-warrior on 9/22/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LifeActivity extends AppCompatActivity {

    private LifeView surfaceView;
    private Thread gameThread;

    private boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);

        surfaceView = (LifeView) findViewById(R.id.surfaceView);

        gameThread = new Thread(surfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!gameThread.isAlive()) {
            gameThread.run();
        } else {
            surfaceView.setRunning(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        surfaceView.setRunning(false);
    }
}