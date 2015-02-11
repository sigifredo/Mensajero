package com.nullpoint.mensajero;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            String messageReceive = "";

            for (Object pdu: pdus)
                messageReceive += SmsMessage.createFromPdu((byte[]) pdu).getMessageBody();

            Toast.makeText(context, messageReceive, Toast.LENGTH_LONG).show();
            abortBroadcast();
        }
    }
}