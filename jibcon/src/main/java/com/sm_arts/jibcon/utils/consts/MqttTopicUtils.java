package com.sm_arts.jibcon.utils.consts;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;

/**
 * Created by jaeyoung on 8/4/17.
 */

public class MqttTopicUtils {
    public static String getSubscribeUri() {
        return "mqtt://" + Configs.Mobius.HOST + "/" + getEndpointOfSubscription() + "?ct=json";
    }

    public static String getSubscribeTopic() {
        return "/oneM2M/req/" + Configs.CSE.NAME + "/" + getEndpointOfSubscription() + "/#";
    }

    public static String getPublishTopic() {
        return "/oneM2M/resp/" + Configs.CSE.NAME + "/" + getEndpointOfSubscription() + "/json";
    }

    public static String getEndpointOfSubscription() {
        return Configs.AE.AID + "_subscription";
    }

    public static String makeSubscriptionSur(String aeName, String cntName) {
        return "/" + Configs.CSE.NAME + "/" + aeName + "/" + cntName + "/" + getEndpointOfSubscription();
    }

    public static String makeSubscriptionSur(DeviceItem item) {
        return makeSubscriptionSur(item.getAeName(), getResponseCnt(item.getCntName()));
    }

    public static String getRequestCnt(String cntName) {
        return cntName + "_req";
    }
    public static String getResponseCnt(String cntName) {
        return cntName + "_res";
    }
}
