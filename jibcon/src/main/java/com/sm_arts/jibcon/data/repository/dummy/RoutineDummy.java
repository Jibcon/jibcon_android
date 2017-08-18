package com.sm_arts.jibcon.data.repository.dummy;

import com.sm_arts.jibcon.data.models.api.dto.Routine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 8/13/17.
 */

public class RoutineDummy {
    public static List<Routine> getDummys() {
        List<Routine> dummy = new ArrayList<>();
        dummy.add(new Routine("너무 추워!", "온도계", "온도", "smaller", "24", "도", "에어컨", "전원", "off"));
        dummy.add(new Routine("어서오세요.", "초음파센서", "출입감지센서", "equal", "on", "on/off", "전구", "전원", "off"));

        return dummy;
    }
}
