package codes.knight.cellularautomata;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Nathan on 11/20/2016.
 */

public class LifeField {

    private int panX, panY;
    private int width;
    private int height;
    private int cellWidth = 50;
    private int cellHeight = 50;
    private boolean[][] field;
    private Paint paint;

    public LifeField(int width, int height) {
        field = new boolean[width][height];
        this.width = width;
        this.height = height;
        paint = new Paint();
        panX = 0;
        panY = 0;
    }

    public void draw(Canvas c) {
        c.drawColor(Color.BLACK);
        paint.setColor(Color.YELLOW);
        c.drawRect(50, 50, 100, 100, paint);
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                paint.setStyle(field[x][y] ? Paint.Style.FILL : Paint.Style.STROKE);
                c.drawRect(x * cellWidth, y * cellHeight, (x + 1) * cellWidth, (y + 1) * cellHeight, paint);
            }
        }
    }

    public int getPanX() {
        return panX;
    }

    public void adjustPanX(int deltaX) {
        this.panX += deltaX;
    }

    public int getPanY() {
        return panY;
    }

    public void adjustPanY(int deltaY) {
        this.panY += deltaY;
    }
}
