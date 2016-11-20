package codes.knight.cellularautomata;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by thee-code-warrior on 9/22/2016.
 */
public class LifeView extends SurfaceView {

    private boolean running = true;
    Paint paint;
    Surface surface;
    Thread thread;
    private LifeField field;

    public LifeView(Context context) {
        super(context);
        paint = new Paint();
        getHolder().addCallback(new SurfaceHolder.Callback() {
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
        });
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
    }

    public LifeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
