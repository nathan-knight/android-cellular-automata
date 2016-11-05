package codes.knight.cellularautomata;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by thee-code-warrior on 9/22/2016.
 */
public class LifeView extends SurfaceView implements Runnable, SurfaceHolder.Callback {

    private boolean running = true;
    Surface surface;

    public LifeView(Context context) {
        super(context);
    }

    public LifeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void run() {
        Canvas canvas = null;
        SurfaceHolder holder = getHolder();
        while (true) {
            if(running) {
                draw();
            }
        }
    }

    private void draw() {
        if(surface == null) surface = getHolder().getSurface();
        Canvas canvas;
        Log.d("LifeActivity", "DRAWING?!");
        if(surface.isValid()) {
            canvas = surface.lockCanvas(null);
            canvas.drawColor(Color.argb(255, 100, 200, 100));
            canvas.drawText("TEST", 100, 100, new Paint());
            surface.unlockCanvasAndPost(canvas);
        }
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawText("Hello world!", 20, 20, new Paint());
//        Log.d("LifeActivity", "DRAWING?!");
////        gameController.draw(canvas);
//    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
//        setWillNotDraw(false);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
