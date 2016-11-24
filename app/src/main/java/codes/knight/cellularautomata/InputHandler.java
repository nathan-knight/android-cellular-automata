package codes.knight.cellularautomata;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;

/**
 * Created by Nathan on 11/23/2016.
 */

public class InputHandler extends ScaleGestureDetector.SimpleOnScaleGestureListener implements GestureDetector.OnGestureListener, Button.OnClickListener { // View.OnTouchListener,

    VelocityTracker velocityTracker = null;
    LifeView lifeView;
    ScaleGestureDetector scaleGestureDetector;
    final String LOG_TAG = this.getClass().getSimpleName();

    public InputHandler(LifeView lifeView) {
        this.lifeView = lifeView;
        scaleGestureDetector = new ScaleGestureDetector(lifeView.getContext(), new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                Log.d(LOG_TAG, "Scale factor: " + scaleGestureDetector.getScaleFactor());
                return false;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                Log.d(LOG_TAG, "onScaleBegin");
                return false;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
                Log.d(LOG_TAG, "onScaleEnd");
            }
        });
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(LOG_TAG, "onDown");
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        lifeView.field.toggleCell(motionEvent.getX(), motionEvent.getY());
        Log.d(LOG_TAG, "onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float dx, float dy) {
        lifeView.field.adjustPanX(-dx);
        lifeView.field.adjustPanY(-dy);
//        Log.d(LOG_TAG, "onScroll called, " + dx + " " + dy);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(LOG_TAG, "onFling");
        return false;
    }

    @Override
    public void onClick(View view) {
        Log.d(LOG_TAG, "Button pressed");
        lifeView.lifeRunning = !lifeView.lifeRunning;
    }
}
