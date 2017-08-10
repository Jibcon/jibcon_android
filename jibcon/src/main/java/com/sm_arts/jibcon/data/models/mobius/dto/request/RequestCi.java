package com.sm_arts.jibcon.data.models.mobius.dto.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaeyoung on 8/9/17.
 */

public class RequestCi {
    @SerializedName("m2m:cin")
    public M2mcin m2mcin = new M2mcin();

    public class M2mcin {
        public String con;
    }
}
