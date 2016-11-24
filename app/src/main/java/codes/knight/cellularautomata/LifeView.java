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

/**
 * Created by thee-code-warrior on 9/22/2016.
 */
public class LifeView extends SurfaceView {

    private boolean running = true;
    Paint paint;
    Surface surface;
    Thread thread;
    public LifeField field;
    InputHandler inputHandler;
    GestureDetectorCompat gestureDetector;
    final String LOG_TAG = this.getClass().getSimpleName();

    public LifeView(Context context) {
        super(context);
        if(isInEditMode()) return;
        Log.d(LOG_TAG, "Creating paint.");
        paint = new Paint();
        Log.d(LOG_TAG, "Created paint.");
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
        Log.d(LOG_TAG, "Creating and launching thread.");
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
        Log.d(LOG_TAG, "Thread launched.");
        inputHandler = new InputHandler(this);
        gestureDetector = new GestureDetectorCompat(getContext(), inputHandler);
        this.setLongClickable(true);
//        gestureDetector.set
//        getContext().gest
//        this.setOnTouchListener(inputHandler);
        /*this.setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                return true;
            }
        });*/
        Log.d(LOG_TAG, "Listeners added");
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
