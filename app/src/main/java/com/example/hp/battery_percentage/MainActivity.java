package com.example.hp.battery_percentage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Declaring variables
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.battery); // Initializing object by ID
        /*
         BroadcastReceiver is an Android component which allows you to register for system or
         application events.
         */
        BroadcastReceiver batteryReceiver = new BroadcastReceiver() {  //Creating object
            @Override
            public void onReceive(Context context, Intent intent) {
                //UnregisterReceiver will unregister a previously registered BroadcastReceiver.
                context.unregisterReceiver(this);
                //Getting the information about the battery
                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;
                //Checks the current level of battery
                if (currentLevel >= 0 && scale > 0) { // if Statement
                    level = (currentLevel * 100) / scale;
                }
                textView.setText("Battery Remaining:" + level + "%"); // Setting text for textView
            }
        };
        /*
         * ACTION_BATTERY_CHANGED is a broadcast containing the charging state, level, and other
           information about the battery.
         * IntentFilter specifies the types of intents to which a broadcast receiver can respond.
         */
        IntentFilter batteryFilter = new IntentFilter //Creating the object of
                (Intent.ACTION_BATTERY_CHANGED);
        // Calling registerReceiver() to register the receiver
        registerReceiver(batteryReceiver, batteryFilter);
    }

}