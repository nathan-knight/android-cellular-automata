package codes.knight.cellularautomata;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Nathan on 11/20/2016.
 */

public class LifeField {

    private float panX, panY;
    private int width;
    private int height;
    private int cellWidth = 100;
    private int cellHeight = 100;
    private float scale = 1;
    private boolean[][] field;
    private Paint paint;
    final String LOG_TAG = this.getClass().getSimpleName();

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
        paint.setColor(Color.GREEN);
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                paint.setStyle(field[x][y] ? Paint.Style.FILL : Paint.Style.STROKE);
                c.drawRect(x * cellWidth + (int) panX, y * cellHeight + (int) panY,
                        (x + 1) * cellWidth + (int) panX, (y + 1) * cellHeight + (int) panY, paint);
            }
        }
    }

    private int getLivingNeighbors(int posX, int posY) {
        int count = 0;
        for(int x = posX - 1; x <= posX + 1; x++) {
            for(int y = posY - 1; y <= posY + 1; y++) {
                if(x == posX && y == posY) continue;
                if(field[x >= width ? 0 : x < 0 ? width - 1 : x][y >= height ? 0 : y < 0 ? height - 1 : y]) count++;
            }
        }
        return count;
    }

    public void tick() {
        boolean[][] nextGeneration = new boolean[width][height];
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                int livingNeighborCount = getLivingNeighbors(x, y);
                if(livingNeighborCount < 2 || livingNeighborCount > 3) {
                    nextGeneration[x][y] = false;
                }
                if(livingNeighborCount == 3) {
                    nextGeneration[x][y] = true;
                }
                if(livingNeighborCount == 2) {
                    nextGeneration[x][y] = field[x][y];
                }
            }
        }
        field = nextGeneration;
    }

    public float getPanX() {
        return panX;
    }

    public void adjustPanX(float deltaX) {
        this.panX += deltaX;
//        Log.d("PAN X", "" + panX);
    }

    public float getPanY() {
        return panY;
    }

    public void adjustPanY(float deltaY) {
        this.panY += deltaY;
//        Log.d("PAN Y", "" + panY);
    }

    public void toggleCell(float x, float y) {
        int cellX = (int) ((x - panX)/cellWidth);
        int cellY = (int) ((y - panY)/cellHeight);
        field[cellX][cellY] = !field[cellX][cellY];
    }

    public void setScale(float scale) {
        this.scale = scale;
        this.cellHeight = (int)(100.0 / this.scale);
        this.cellWidth = (int)(100.0 / this.scale);
    }

    public void clear() {
        this.field = new boolean[width][height];
    }
}
