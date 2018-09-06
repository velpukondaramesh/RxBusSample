package com.zappstech.rxbussample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zappstech.rxbussample.rx.User;
import com.zappstech.rxbussample.rxbus.Bus;
import com.zappstech.rxbussample.rxbus.BusProvider;
import com.zappstech.rxbussample.rxbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    //private final EventBus eventBus = EventBus.getDefault();
    private LinearLayout linearLayout;
    private Bus eventBus = BusProvider.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.layout);
        //eventBus.register(this);
        eventBus.register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //eventBus.unregister(this);
        eventBus.unregister(this);
        eventBus = null;
    }

    /*@Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String event) {
        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(this);
        tv.setLayoutParams(lparams);
        tv.setText(event);
        linearLayout.addView(tv);
    }*/

    @Subscribe
    public void onEvent(User event) {
        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(this);
        tv.setLayoutParams(lparams);
        tv.setText(event.getText());
        linearLayout.addView(tv);
    }
}
