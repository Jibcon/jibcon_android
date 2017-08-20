package com.sm_arts.jibcon.utils.consts;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jaeyoung on 8/2/17.
 */

public interface Configs {
    interface Mobius {
        String HOST = "13.124.172.12";
        String PORT = "7579";
    }

    interface Mqtt {
        String HOST = "13.124.172.12";
        String PORT = "1883";
        int QOS = 1; /* 0: NO QoS, 1: No Check , 2: Each Check */
    }

    interface CSE {
        String NAME = "Mobius";
    }

    interface AE {
        String NAME = "ae-jibcon";
        String AID = "aei-jibcon";
    }


    interface DEVICES_SUPPORTABLE {

        List<String> DEVICENAME_CHOCIED = Arrays.asList(
                "led",
                "ultra",
                "humidity",
                "Philips Hue 전구",
                "Philips Hue 전구 - 현관"
        );

        List<String> DEVICECOM_CHOICES = Arrays.asList(
                "smArts",
                "Philips"
        );

        List<String> DEVICETYPE_CHOICES = Arrays.asList(
                "bulb",
                "ultrasensor",
                "humiditysensor",
                "hue-bulb",
                "hue-bulb-현관"
        );

        List<String> AENAME_CHOICES = Arrays.asList(
                "ae-smarts",
                "ae-smarts",
                "ae-smarts",
                "ae-smarts",
                "ae-smarts"
        );

        List<String> CNTNAME_CHOICES = Arrays.asList(
                "cnt-led",
                "cnt-ultra",
                "cnt-dht",
                "cnt-hue",
                "cnt-hue"
        );
    }





}
