package com.sm_arts.jibcon.data.models.api.dto.hue;

/**
 * Created by admin on 2017-11-12.
 */

public class error {
    public String type;
    public String address;
    public String description;

    @Override
    public String toString() {
        return "type : "+type+"\n"+"address : "+ address+"\n"+"description : "+description;
    }
}
