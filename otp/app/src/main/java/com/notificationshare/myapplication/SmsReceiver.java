package com.notificationshare.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    private static final String SMS_ACTION="android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG= "SmsBroadcast";
    String sms, phone="";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Log.d(TAG,intent.getAction());
        if (intent.getAction()== SMS_ACTION){
            Bundle bundle= intent.getExtras();
            if (bundle!= null){
                Object[] pdu= (Object[]) bundle.get("pdus");
                final SmsMessage[] message= new SmsMessage[pdu.length];
                for (int i=0; i<pdu.length; i++){
                    if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                        String format= bundle.getString("format");
                        message[i]= SmsMessage.createFromPdu((byte[])pdu[i], format);
                    }else {
                        message[i]= SmsMessage.createFromPdu((byte[])pdu[i]);
                    }
                    sms= message[i].getMessageBody();
                    phone= message[i].getOriginatingAddress();
                }
                Toast.makeText(context, "message:"+sms+"\nphone:"+phone, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
