package codes.knight.cellularautomata;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.widget.Button;

/**
 * Created by thee-code-warrior on 9/22/2016.
 */
public class LifeView extends SurfaceView {

    private boolean running = true;
    Paint paint;
    Surface surface;
    Thread thread;
    public LifeField field;
    public boolean lifeRunning = false;
    InputHandler inputHandler;
    GestureDetectorCompat gestureDetector;
    final String LOG_TAG = this.getClass().getSimpleName();

    public LifeView(Context context) {
        super(context);
        if(isInEditMode()) return;
        Log.d(LOG_TAG, "Creating paint.");
        paint = new Paint();
        Log.d(LOG_TAG, "Created paint.");
        field = new LifeField(100, 100);
        Log.d(LOG_TAG, "Creating and launching thread.");
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long lastTick = 0;
                while(running) {
                    Canvas c = getHolder().lockCanvas();
                    if(c != null) {
                        field.draw(c);
                        getHolder().unlockCanvasAndPost(c);
                    }
                    if(System.currentTimeMillis() - lastTick > 1000 && lifeRunning) {
                        lastTick = System.currentTimeMillis();
                        field.tick();
                    }
                }
            }
        });
        thread.start();
        Log.d(LOG_TAG, "Thread launched.");
        inputHandler = new InputHandler(this);
        gestureDetector = new GestureDetectorCompat(getContext(), inputHandler);
        this.setLongClickable(true);
        Log.d(LOG_TAG, "Listeners added");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Button buttonPausePlay = (Button) getRootView().findViewById(R.id.buttonPausePlay);
        buttonPausePlay.setOnClickListener(inputHandler);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public LifeView(Context context, AttributeSet attrs) {
        this(context);
    }

}
