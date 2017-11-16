package com.sm_arts.jibcon.ui.main.cheatkey.passive;

import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.data.models.api.dto.RoutineItem;

/**
 * Created by admin on 2017-11-15.
 */

public class ConverUtils {
    private static final String TAG = "ConverUtils";


    public static RoutineItem convertRoutineItem(LinkedTreeMap<String,Object> item) {
        RoutineItem routineItem = new RoutineItem();
        routineItem.data = (LinkedTreeMap<String,Object>)item.get("data");
        return routineItem;
    }
}
