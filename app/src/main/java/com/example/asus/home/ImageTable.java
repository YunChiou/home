package com.example.asus.home;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageTable extends Table {

    Drawable table;

    public ImageTable(Drawable table, int left, int top) {
        this.table = table;
        this.boundingBoxLeft = left;
        this.boundingBoxTop = top;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        Bitmap chairBitmap = ((BitmapDrawable) table).getBitmap();
        canvas.drawBitmap(chairBitmap, null, new RectF(boundingBoxLeft, boundingBoxTop, boundingBoxLeft + 600, boundingBoxTop + 600), paint);
    }

    @Override
    public boolean isInside(int left, int top) {
        if (this.boundingBoxLeft < left && boundingBoxLeft + width > left && this.boundingBoxTop < top && boundingBoxTop + height > top) {
            return true;
        }
        return false;
    }

    @Override
    public void setTable(int left, int top) {
        this.boundingBoxLeft = left;
        this.boundingBoxTop = top;
    }
}
