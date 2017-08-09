package com.sm_arts.jibcon.data.models.mobius.dto.response;

import com.google.gson.annotations.SerializedName;
import com.sm_arts.jibcon.data.models.mobius.dto.request.RequestAe;

/**
 * Created by jaeyoung on 8/2/17.
 */

public class ResponseAe {
    @SerializedName("m2m:ae")
    public RequestAe.M2mae m2mae;

    class M2mae {
        public String pi;
        public int ty;
        public String ct;
        public String ri;
        public String rn;
        public String lt;
        public String et;
        public String api;
        public String aei;
        public boolean rr;
    }
}
