package com.nullpoint.mensajero;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        Cursor cursor = getContentResolver().query(Uri.withAppendedPath(Uri.parse("content://sms"), "inbox"), null, null, null, null);

        if (cursor.moveToFirst()) {

            while (cursor.moveToNext()) {
                Log.i("address", cursor.getString(cursor.getColumnIndex("address")).toString());
            }

            Toast.makeText(this, "moveToFirst", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "NO moveToFirst", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings)
            return true;
        else
            return super.onOptionsItemSelected(item);
    }

    // @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();

        /*
        if (Telephony.Sms.getDefaultSmsPackage(this).equals(getPackageName()))
            Toast.makeText(this, "es el paquete por defecto", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "NO es el paquete por defecto " + Telephony.Sms.getDefaultSmsPackage(this), Toast.LENGTH_SHORT).show();
        */
    }

    public void setDefault(View view) {
        Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
        intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, getPackageName());
        startActivity(intent);
    }
}
