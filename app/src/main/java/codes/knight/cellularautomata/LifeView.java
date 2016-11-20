package codes.knight.cellularautomata;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.VelocityTracker;
import android.view.View;

/**
 * Created by thee-code-warrior on 9/22/2016.
 */
public class LifeView extends SurfaceView {

    private boolean running = true;
    Paint paint;
    Surface surface;
    Thread thread;
    private LifeField field;
    VelocityTracker velocityTracker = null;
    final String LOG_TAG = this.getClass().getSimpleName();

    public LifeView(Context context) {
        super(context);
        if(isInEditMode()) return;
        paint = new Paint();
        /*getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                Canvas c = getHolder().lockCanvas();
                if(c != null) {
                    c.drawColor(Color.BLACK);
                    paint.setColor(Color.YELLOW);
                    c.drawRect(50f, 90f, 500f, 500f, paint);
                    getHolder().unlockCanvasAndPost(c);
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });*/
        field = new LifeField(100, 100);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(running) {
                    Canvas c = getHolder().lockCanvas();
                    if(c != null) {
                        field.draw(c);
                        getHolder().unlockCanvasAndPost(c);
                    }
                }
            }
        });
        thread.start();
        this.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        if(velocityTracker == null)
                            velocityTracker = VelocityTracker.obtain();
                        else
                            velocityTracker.clear();
                        velocityTracker.addMovement(motionEvent);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        velocityTracker.addMovement(motionEvent);
                        velocityTracker.computeCurrentVelocity(20);
                        field.adjustPanX(VelocityTrackerCompat.getXVelocity(velocityTracker, motionEvent.getPointerId(motionEvent.getActionIndex())));
                        field.adjustPanY(VelocityTrackerCompat.getYVelocity(velocityTracker, motionEvent.getPointerId(motionEvent.getActionIndex())));
                        break;
                    case MotionEvent.ACTION_UP:
                        velocityTracker.addMovement(motionEvent);
                        velocityTracker.computeCurrentVelocity(20);
                        Log.d(LOG_TAG, "Touch ACTION_UP");
                        field.toggleCell(motionEvent.getX(), motionEvent.getY());
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        velocityTracker.recycle();
                        break;
                }
                return true;
            }
        });
        this.setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                return true;
            }
        });
        Log.d(LOG_TAG, "Listeners added");
    }

    public LifeView(Context context, AttributeSet attrs) {
        this(context);
    }

}
