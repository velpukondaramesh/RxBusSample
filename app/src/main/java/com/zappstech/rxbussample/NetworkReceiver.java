package com.zappstech.rxbussample;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zappstech.rxbussample.rx.User;
import com.zappstech.rxbussample.rxbus.Bus;
import com.zappstech.rxbussample.rxbus.BusProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NetworkReceiver extends BroadcastReceiver {

    //private final EventBus eventBus = EventBus.getDefault();
    private Bus eventBus = BusProvider.getInstance();

    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkState.getConnectivityStatusString(context);
        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        String eventData = "@" + formattedDate + ": device network state: " + status;
        //eventBus.post(eventData);

        //eventBus.register(this);

        User user = new User();
        user.setText(eventData);

        eventBus.post(user);

    }
}
