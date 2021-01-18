package com.example.mob204_lab3.Bai2;

import android.graphics.Bitmap;

public class ImageOnListView {
    Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ImageOnListView() {
    }

    public ImageOnListView(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
