package com.example.admin.jipcon2.Device;

import android.graphics.Bitmap;

/**
 * Created by admin on 2017-04-06.
 */

public class DeviceItem {
    Bitmap image;//디바이스 메뉴의 이미지
    String itemname;//디바이스 메뉴 아이템 이름 ex) 전등 알람 등등..

    public DeviceItem(Bitmap image, String itemname) {
        this.image = image;
        this.itemname = itemname;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
}
