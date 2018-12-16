package android.example.com.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    // CB 6. Initialize same constant variable
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        // SB 5. receive the intent action
        String intentAction = intent.getAction();

        String musicIntentAction = intent.getAction();

        // SB 6. null check
        // SB 7. instructing the receiver to send a toast when power is connected or disconnected
        // CB 7. instruct the custom receiver on what to do also
        if (intentAction != null) {
            String toastMessage = "unknown intent action";
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power connected!";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power disconnected!";
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    toastMessage = "Custom Broadcast Received";
                    break;
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }

        if (musicIntentAction != null) {
            String isHeadsetPlugged = "unknown intent action";
            switch (musicIntentAction) {
                case Intent.ACTION_HEADSET_PLUG:
                    isHeadsetPlugged = "Headset connected!";
                    break;
            }
            Toast.makeText(context, isHeadsetPlugged, Toast.LENGTH_SHORT).show();
        }

    }
}
