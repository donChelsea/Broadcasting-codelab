package android.example.com.powerreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    // CB 1. create a constrant variable to send a customer broadcast (from the app)
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    // SB 1. Initialize a broadcast receiver
    CustomReceiver customReceiver = new CustomReceiver();
    CustomReceiver musicReceiver = new CustomReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SB 2. initialize an intent with specific actions for the receiver to look for
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        // SB 3. register the receiver with the intent. will always be active as long as main activity is running
        this.registerReceiver(customReceiver, intentFilter);

        // CB 4. register the receiver with localbroadcastmanager class
        LocalBroadcastManager.getInstance(this).registerReceiver(customReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

        IntentFilter musicIntent = new IntentFilter();
        musicIntent.addAction(Intent.ACTION_HEADSET_PLUG);

        this.registerReceiver(musicReceiver, musicIntent);
    }

    @Override
    protected void onDestroy() {
        // SB 4. ensure that the receiver is unregistered when the app activity is destroyed
        this.unregisterReceiver(customReceiver);

        this.unregisterReceiver(musicReceiver);
        super.onDestroy();

        // CB 5. unregister the same receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(customReceiver);
    }

        // CB 2. Create a button to send the broadcast with onClick method
        // CB 3. creae an intent in method and implement sendbroadcast from localbroadcastmanager class
    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
}
