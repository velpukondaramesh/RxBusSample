package com.zappstech.rxbussample.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zappstech.rxbussample.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.flowables.ConnectableFlowable;

public class MainActivity extends AppCompatActivity {

    private RxBus _rxBus;
    private CompositeDisposable _disposables;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.layout);
        _rxBus = new RxBus();
    }

    @Override
    protected void onStart() {
        super.onStart();
        _disposables = new CompositeDisposable();
        ConnectableFlowable<Object> tapEventEmitter = _rxBus.asFlowable().publish();

        _disposables.add(tapEventEmitter.subscribe(
                event -> {
                    if (event instanceof User) {
                        //Toast.makeText(MainActivity.this, ((User) event).getText(), Toast.LENGTH_LONG).show();
                        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(lparams);
                        tv.setText(((User) event).getText());
                        linearLayout.addView(tv);
                    }
                }));

        /*_disposables.add(tapEventEmitter
                .publish(stream -> stream.buffer(stream.debounce(1, TimeUnit.SECONDS)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(taps -> {
                    _showTapCount(taps.size());
                }));*/

        _disposables.add(tapEventEmitter.connect());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        _disposables.clear();
    }
}
