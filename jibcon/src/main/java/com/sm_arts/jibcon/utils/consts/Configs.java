package com.sm_arts.jibcon.utils.consts;

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
        
        interface DEVICENAME_CHOCIED {
            String LED = "led";
            String ULTRA = "ultra";

        }

        interface DEVICECOM_CHOICES {
            String SMARTS = "smarts";
        }

        interface DEVICETYPE_CHOICES {
            String BULB = "bulb";
            String SENSOR = "sensor";

        }

        interface AENAME_CHOICES {
            String AE_SECONDLED = "ae-secondled";
            String AE_JYP = "ae-jyp";
        }

        interface CNTNAME_CHOICES {
            String CNT_LED = "cnt-led";
            String CNT_ULTRA = "cnt-ultra";
        }
    }





}
