package com.sm_arts.jibcon.utils.mqtt;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by jaeyoung on 7/29/17.
 */

public class RetrieveRequest extends Thread {
    private static final String TAG = "RetrieveRequest";

    private IReceived receiver;

    public RetrieveRequest() {}
    public void setReceiver(IReceived hanlder) { this.receiver = hanlder; }

    @Override
    public void run() {
        try {
            String sb = Config.CSE.SERVICE_URL + "/" + Config.Sensor.AE.NAME+ "/" + Config.Sensor.CNT.NAME + "/" + "latest";

            URL mUrl = new URL(sb);
            Log.d(TAG, "run: GET url=[" + sb + "]");

            HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(false);

            conn.setRequestProperty("Accept", "application/xml");
            conn.setRequestProperty("X-M2M-RI", "12345");
            conn.setRequestProperty("X-M2M-Origin", Config.AndroidClient.AE.AEID);
            conn.setRequestProperty("nmtype", "long");
            conn.connect();

            String strResp = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));


            String strLine= "";
            while ((strLine = in.readLine()) != null) {
                strResp += strLine;
            }

            if ( strResp != "" ) {
                receiver.getResponseBody(strResp);
            }
            conn.disconnect();

        } catch (Exception exp) {
            Log.w(TAG, "run: " + exp.getMessage());
        }
    }
}