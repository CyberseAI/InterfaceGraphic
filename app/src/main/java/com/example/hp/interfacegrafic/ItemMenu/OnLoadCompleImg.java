package com.example.hp.interfacegrafic.ItemMenu;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public interface OnLoadCompleImg {
    int getCount();

    Object getItem(int position);

    long getItemId(int position);

    View getView(int position, View convertView, ViewGroup parent);

    public void  setLoadImage(ImageView container, Bitmap img);
    public void OnloadCompleteImgResult(ImageView img, int position, Bitmap imgsourceimg);
}
