package com.gejiahui.androidpractice.dragrecyclerview;

/**
 * Created by gejiahui on 2016/5/18.
 */
public class DragItem {

    String itemName;
    int imgRes;

    public DragItem(String itemName, int imgRes) {
        this.itemName = itemName;
        this.imgRes = imgRes;
    }

    public String getItemName() {
        return itemName;
    }

    public int getImgRes() {
        return imgRes;
    }
}
