package com.example.amirhossein.networkintro;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity implements View.OnClickListener {

    private Handler uiHandler = new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg) {
            if(msg.what==0) {
                TextView result = (TextView) findViewById(R.id.text2);
                result.setText((String)msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myB = (Button) findViewById(R.id.button1);
        myB.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        SClient sc = new SClient(uiHandler);
        Thread t = new Thread(sc);
        t.start();
    }
}
