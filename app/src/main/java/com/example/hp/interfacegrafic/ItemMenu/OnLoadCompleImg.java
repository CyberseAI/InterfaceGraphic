package com.example.hp.interfacegrafic.ItemMenu;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public interface OnLoadCompleImg {
    public void  setLoadImage(ImageView container, Bitmap img);
    public void OnloadCompleteImgResult(ImageView img, int position, Bitmap imgsourceimg);
}
