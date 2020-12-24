package com.example.wifisignalstrength;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Vector;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {
    static WifiManager wifiManager;
    static TextView strengthValue, evaluateValue, history1, history2, history3;
    static Button enterButton;
    ArrayList<String> results = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);

        strengthValue = (TextView) findViewById(R.id.signalValue_textView);
        evaluateValue = (TextView) findViewById(R.id.signalEvaluate);
        enterButton = (Button) findViewById(R.id.enter_button);
        history1 = (TextView) findViewById(R.id.history1);
        history2 = (TextView) findViewById(R.id.history2);
        history3 = (TextView) findViewById(R.id.history3);


        results.add("0");
        results.add("0");
        results.add("0");

    }

    public void onReceive(WifiManager wifiManager) {
        int numberOfLevels = 5;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        //int level=WifiManager.calculateSignalLevel(wifiInfo.getRssi(), numberOfLevels);

        int temp = wifiInfo.getRssi();
        strengthValue.setText(Integer.toString(temp) + " dbm");

        if(temp > -50){
            evaluateValue.setText("Excellent Signal");
        }
        else if(temp <= -50 && temp > -60){
            evaluateValue.setText("Good Signal");
        }
        else if(temp <= -60 && temp > -70){
            evaluateValue.setText("Fair Signal");
        }
        else{
            evaluateValue.setText("Weak Signal");
        }
        history1.setText("1. Wifi name  " + wifiInfo.getSSID());
        history2.setText("2. IP Addres " + wifiInfo.getIpAddress());
        history3.setText("3. Network ID " + wifiInfo.getNetworkId());


    }

    public void onClickEnter(View view) {
        onReceive(wifiManager);
    }

}