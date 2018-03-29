package com.example.herbert.fightgo.Model;

/**
 * Created by Administrator on 2018/3/29.
 */

public class RecyclerItemModel {
    private String title;
    private int bitmap;

    public RecyclerItemModel(String title, int bitmap) {
        this.title = title;
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBitmap() {
        return bitmap;
    }

    public void setBitmap(int bitmap) {
        this.bitmap = bitmap;
    }
}
