package com.sm_arts.jibcon.utils.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.splash.tutorial.IntroActivity;

import java.util.Map;

/**
 * Created by admin on 2017-09-15.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{

    private static final String TAG = FirebaseMessagingService.class.getName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG,"onMessageReceived");

        String title=remoteMessage.getNotification().getTitle();
        String body=remoteMessage.getNotification().getBody();
        sendPushNotification(title, body);
        Map<String,String> map =remoteMessage.getData();
        //Log.d("onMessageReceived",map.get("_id"));
    }

    private void sendPushNotification(String title, String body) {
        Log.d(TAG,"sendPushNotification");
        Intent intent = new Intent(this, IntroActivity.class);
        intent.putExtra("testing","testing");


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // startActivity(intent);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 12 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.jibcon_logo).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.jibcon_logo) )
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri).setLights(000000255,500,2000)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakelock.acquire(5000);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
