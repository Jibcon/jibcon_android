package com.example.admin.jipcon2.Device;

/**
 * Created by admin on 2017-04-06.
 */

public class DeviceItem {
    int deviceType;//디바이스 메뉴의 int 값. int 값으로 판별
    String itemname;//디바이스 메뉴 아이템 이름 ex) 전등 알람 등등..


//    strarr.add(0,"airconditioner");
//    strarr.add(1,"lightbulb");
//    strarr.add(2,"fan");
//    strarr.add(3,"refrigerator");
    //0 : 에어컨
    //1 : 전구
    //2 : 선풍기
    //3 : 냉장고

    public DeviceItem(int deviceType, String itemname) {
        this.deviceType = deviceType;
        this.itemname = itemname;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
}
