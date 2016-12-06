package codes.knight.cellularautomata.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.Button;

import codes.knight.cellularautomata.Handler.InputHandler;
import codes.knight.cellularautomata.LifeField;
import codes.knight.cellularautomata.R;

/**
 * Created by thee-code-warrior on 9/22/2016.
 * View that will contain rendered LifeField.
 */
public class LifeView extends SurfaceView {

    private boolean running = true;
    Paint paint;
    Thread thread;
    public LifeField field;
    public long millisPerTick = 100;
    public boolean lifeRunning = false;
    InputHandler inputHandler;
    GestureDetectorCompat gestureDetector;
    final String LOG_TAG = this.getClass().getSimpleName();

    public LifeView(Context context) {
        super(context);
        initialSetup(null);
        createThread();
    }

    private void initialSetup(LifeField restoredField) {
        if(isInEditMode()) return;
        Log.d(LOG_TAG, "Creating paint.");
        paint = new Paint();
        Log.d(LOG_TAG, "Created paint.");
        if(restoredField == null) field = new LifeField(100, 100);
        else field = restoredField;
        inputHandler = new InputHandler(this);
        gestureDetector = new GestureDetectorCompat(getContext(), inputHandler);
        this.setLongClickable(true);
        Log.d(LOG_TAG, "Listeners added");
    }

    private void createThread() {
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
                    if(System.currentTimeMillis() - lastTick > millisPerTick && lifeRunning) {
                        lastTick = System.currentTimeMillis();
                        field.tick();
                    }
                }
            }
        });
        running = true;
        thread.start();
        Log.d(LOG_TAG, "Thread launched.");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Button buttonPausePlay = (Button) getRootView().findViewById(R.id.buttonPausePlay);
        buttonPausePlay.setOnClickListener(inputHandler);
        Button buttonFaster = (Button) getRootView().findViewById(R.id.buttonFaster);
        buttonFaster.setOnClickListener(inputHandler);
        Button buttonSlower = (Button) getRootView().findViewById(R.id.buttonSlower);
        buttonSlower.setOnClickListener(inputHandler);
        Button buttonClear = (Button) getRootView().findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(inputHandler);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(inputHandler.onRawTouch(event)) return true;
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public LifeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialSetup(null);
        createThread();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        super.onSaveInstanceState();
        running = false;
        Bundle bundle = new Bundle();
        bundle.putParcelable("field", field);
        Log.d(LOG_TAG, "Wrote field to bundle (onSaveInstanceState)");
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable parcel) {
        super.onSaveInstanceState();
        if(parcel instanceof Bundle) {
            Log.d(LOG_TAG, "Loading field from bundle (onRestoreInstanceState)");
            Bundle bundle = (Bundle) parcel;
            initialSetup((LifeField) bundle.getParcelable("field"));
        }
    }

    public void onResume() {
        createThread();
    }

    public void onPause() {
        running = false;
    }
}
