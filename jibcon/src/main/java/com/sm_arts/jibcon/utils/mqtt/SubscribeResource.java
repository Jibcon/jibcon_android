package com.sm_arts.jibcon.utils.mqtt;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jaeyoung on 7/28/17.
 */

class SubscribeResource extends Thread {
    private static final String TAG = "SubscribeResource";
    private IReceived receiver;

    public ContentSubscribeObject subscribeInstance;
    public SubscribeResource() {
        subscribeInstance = new ContentSubscribeObject();
        subscribeInstance.setUrl(Config.CSE.HOST);
        subscribeInstance.setResourceName(Config.AndroidClient.AE.AEID+"_rn");
        subscribeInstance.setPath(Config.AndroidClient.AE.AEID+"_sub");
        //subscribeInstance.setPath(Config.AndroidClient.AE.AEID+"_sub");
        ///oneM2M/req/Mobius/ae-jibcon_sub/#
//            subscribeInstance.setPath("ae-jibcon");
        subscribeInstance.setOrigin_id(Config.AndroidClient.AE.AEID);
    }
    public void setReceiver(IReceived hanlder) { this.receiver = hanlder; }

    @Override
    public void run() {
        try {
            String sb = Config.CSE.SERVICE_URL + "/" + Config.Sensor.AE.NAME + "/" + Config.Sensor.CNT.NAME;

            URL mUrl = new URL(sb);

            HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(false);

            conn.setRequestProperty("Accept", "application/xml");
            conn.setRequestProperty("Content-Type", "application/vnd.onem2m-res+xml; ty=23");
            conn.setRequestProperty("locale", "ko");
            conn.setRequestProperty("X-M2M-RI", "12345");
            conn.setRequestProperty("X-M2M-Origin", Config.AndroidClient.AE.AEID);

            String reqmqttContent = subscribeInstance.makeXML();
            conn.setRequestProperty("Content-Length", String.valueOf(reqmqttContent.length()));
            Log.d(TAG, "run: url=[" + sb + "]");
            Log.d(TAG, "run: reqmqttContent=[" + reqmqttContent + "]");

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.write(reqmqttContent.getBytes());
            dos.flush();
            dos.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String resp = "";
            String strLine="";
            while ((strLine = in.readLine()) != null) {
                resp += strLine;
            }

            if (resp != "") {
                receiver.getResponseBody(resp);
            }
            conn.disconnect();

        } catch (Exception exp) {
            Log.d(TAG, "run: " + exp.getMessage());
        }
    }
}