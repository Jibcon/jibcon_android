package com.sm_arts.jibcon.utils.consts;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jaeyoung on 8/2/17.
 */

public interface Configs {
    interface HouseConfigs{
        String HouseName = "smArtsOffice";
    }
    interface SubName{
        String subName = "smArtsOffice_subscription";
    }
    interface SubscriptionTopic{
        String topic = "/oneM2M/req/Mobius/"+ SubName.subName+"/json";
    }


    interface Mobius {
        String HOST = "52.79.180.194";
        String PORT = "7579";
    }

    interface Mqtt {
        String HOST = "52.79.180.194";
        String PORT = "1883";
        int QOS = 1; /* 0: NO QoS, 1: No Check , 2: Each Check */
    }

    interface CSE {
        String NAME = "Mobius";
    }

    interface AE {
        String NAME = "ae-smarts";

        String AID = "aei-jibcon";
    }


    interface DEVICES_SUPPORTABLE {

        List<String> DEVICENAME_CHOCIED = Arrays.asList(
                "led",
                "ultra",
                "humitidy",
                "Philips Hue 전구"
        );

        List<String> DEVICECOM_CHOICES = Arrays.asList(
                "smArts",
                "Philips"
        );

        List<String> DEVICETYPE_CHOICES = Arrays.asList(
                "bulb",
                "ultrasensor",
                "humiditysensor",
                "hue-bulb"
        );

        List<String> AENAME_CHOICES = Arrays.asList(
                "ae-smarts",
                "ae-smarts",
                "ae-smarts",
                "ae-smarts"
        );

        List<String> CNTNAME_CHOICES = Arrays.asList(
                "cnt-led",
                "cnt-ultra",
                "cnt-dht",
                "cnt-hue"
        );
    }





}
