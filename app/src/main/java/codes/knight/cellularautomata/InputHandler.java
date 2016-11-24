package codes.knight.cellularautomata;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;

/**
 * Created by Nathan on 11/23/2016.
 */

public class InputHandler implements GestureDetector.OnGestureListener { // View.OnTouchListener,

    VelocityTracker velocityTracker = null;
    LifeView lifeView;
    final String LOG_TAG = this.getClass().getSimpleName();

    public InputHandler(LifeView lifeView) {
        this.lifeView = lifeView;
    }
//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        switch(motionEvent.getActionMasked()) {
//            case MotionEvent.ACTION_DOWN:
//                if(velocityTracker == null)
//                    velocityTracker = VelocityTracker.obtain();
//                else
//                    velocityTracker.clear();
//                velocityTracker.addMovement(motionEvent);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                velocityTracker.addMovement(motionEvent);
//                velocityTracker.computeCurrentVelocity(20);
//                lifeView.field.adjustPanX(VelocityTrackerCompat.getXVelocity(velocityTracker, motionEvent.getPointerId(motionEvent.getActionIndex())));
//                lifeView.field.adjustPanY(VelocityTrackerCompat.getYVelocity(velocityTracker, motionEvent.getPointerId(motionEvent.getActionIndex())));
//                break;
//            case MotionEvent.ACTION_UP:
//                velocityTracker.addMovement(motionEvent);
//                velocityTracker.computeCurrentVelocity(20);
//                Log.d(LOG_TAG, "Touch ACTION_UP");
//                lifeView.field.toggleCell(motionEvent.getX(), motionEvent.getY());
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                velocityTracker.recycle();
//                break;
//        }
//        return true;
//    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(LOG_TAG, "onDown");
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
}
