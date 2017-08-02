package com.sm_arts.jibcon.data.models.mobius.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 8/2/17.
 */

public class RequestSub {
    @SerializedName("m2m:sub")
    public M2msub m2msub;

    public RequestSub() {
        m2msub = new M2msub();
    }

    public class M2msub {
        public String rn;
        public Enc enc;
        public List<String> nu;
        public int nct;
        public int pn;

        public M2msub() {
            enc = new Enc();
            nu = new ArrayList<>();
        }
    }

    public class Enc {
        public List<Integer> net;

        public Enc() {
            net = new ArrayList<>();
        }
    }
}
