package com.sm_arts.jibcon.utils.housemanager;

import android.content.Context;
import android.content.Intent;

import com.sm_arts.jibcon.data.models.api.dto.HouseInfo;
import com.sm_arts.jibcon.data.repository.helper.HouseNetworkManager;
import com.sm_arts.jibcon.ui.splash.tutorial.IntroActivity;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-11-30.
 */

public class JibconHouseManager {
    private static JibconHouseManager obj = null;
    private static List<HouseInfo> myHouseList = new ArrayList<>();
    private static HouseInfo mCurrentHouse = null;

    public static JibconHouseManager getInstance() {
        if (obj == null) {
            synchronized (JibconHouseManager.class) {
                if (obj == null)
                    obj = new JibconHouseManager();
            }
        }
        return obj;
    }

    public  HouseInfo getmCurrentHouse() {
        return mCurrentHouse;
    }

    public void setmCurrentHouse(HouseInfo mCurrentHouse) {
        JibconHouseManager.mCurrentHouse = mCurrentHouse;
        JibconLoginManager.getInstance().setCurrentHouseIdOnSucess(mCurrentHouse.house_id);

    }

    public List<HouseInfo> getMyHouseList() {
        return myHouseList;
    }

    public void setMyHouseList(List<HouseInfo> myHouseList) {
        JibconHouseManager.myHouseList = myHouseList;
    }

    public void addNewHouse(HouseInfo houseInfo) {
        JibconHouseManager.myHouseList.add(houseInfo);
    }

    public void changeCurrentHouse(HouseInfo nextHouse, Context context) {
        HouseNetworkManager.getInstance().changeCurrentHouse(nextHouse);

        Intent intent = new Intent(context, IntroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }
}
