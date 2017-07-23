package com.example.asus.home;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageChair extends Table{

    Drawable chair;

    public ImageChair(Drawable chair, int left, int top) {
        this.chair = chair;
        this.boundingBoxLeft = left;
        this.boundingBoxTop = top;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        Bitmap chairBitmap = ((BitmapDrawable) chair).getBitmap();
        canvas.drawBitmap(chairBitmap, null, new RectF(boundingBoxLeft, boundingBoxTop, boundingBoxLeft + 200, boundingBoxTop + 200), paint);
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
