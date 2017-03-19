package com.example.asus.home;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBar;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class RectangleTable extends Table {

    public RectangleTable(int left, int top) {
        this.boundingBoxLeft = left;
        this.boundingBoxTop = top;
    }

    public RectangleTable(int id, String tableType, int boundingBoxLeft, int boundingBoxTop, int width, int height, String text) {
        this.id = id;
        this.tableType = tableType;
        this.boundingBoxLeft = boundingBoxLeft;
        this.boundingBoxTop = boundingBoxTop;
        this.width = width;
        this.height = height;
        this.tableNumber = text;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        //內圍
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(boundingBoxLeft, boundingBoxTop, boundingBoxLeft + width, boundingBoxTop + height, paint);
        super.draw(canvas, paint);
        paint.setStrokeWidth(0);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(boundingBoxLeft + BORDER_WIDTH, boundingBoxTop + BORDER_WIDTH, boundingBoxLeft + width - BORDER_WIDTH, boundingBoxTop + height - BORDER_WIDTH, paint);
        //文字
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText(tableNumber, boundingBoxLeft + 10, boundingBoxTop + 50, paint);

        QRCodeWriter writer = new QRCodeWriter();
        try {
            if (!condition.equals("")) {
                BitMatrix bitMatrix = writer.encode(condition, BarcodeFormat.QR_CODE, 512, 512);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                    }
                }

                paint.setColor(Color.RED);
                canvas.drawBitmap(bmp, 0, 0, paint);
            }

        } catch (WriterException e) {
            e.printStackTrace();
        }
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