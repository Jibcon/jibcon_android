package com.sm_arts.jibcon.data.models.mobius.dto.request;

/**
 * Created by admin on 2017-10-16.
 */

public class RequestSubscription {
    public String aeName;
    public String cntName;
    public String subName;
    public String receiver;
    public String topic;
    public void setInfo(String aeName, String cntName, String subName,String receiver, String topic)
    {
        this.aeName = aeName;
        this.cntName = cntName;
        this.subName = subName;
        this.receiver = receiver;
        this.topic = topic;
    }
}
