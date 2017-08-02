package com.sm_arts.jibcon.data.models.mobius.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaeyoung on 8/2/17.
 */

public class RequestAe {
    @SerializedName("m2m:ae")
    public M2mae m2mae;

    class M2mae {
        public String rn;
        public String api;
        public boolean rr;
    }
}
