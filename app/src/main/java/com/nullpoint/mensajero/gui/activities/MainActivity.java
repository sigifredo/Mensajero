package com.nullpoint.mensajero.gui.activities;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Toast;

import com.nullpoint.mensajero.ChatActivity;
import com.nullpoint.mensajero.R;
import com.nullpoint.mensajero.gui.fragments.ContactsFragment;
import com.nullpoint.mensajero.gui.fragments.NavigationDrawerFragment;


public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, ContactsFragment.OnContactInteraction {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
        mTitle = getTitle();

        // Cursor in the sms
        /*
        Cursor cursor = getContentResolver().query(Uri.withAppendedPath(Uri.parse("content://sms"), "inbox"), null, null, null, null);

        if (cursor.moveToFirst()) {

            while (cursor.moveToNext()) {
                Log.i("address", cursor.getString(cursor.getColumnIndex("address")).toString());
            }

            Toast.makeText(this, "moveToFirst", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "NO moveToFirst", Toast.LENGTH_SHORT).show();
        }
        */
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;

        switch (position) {
        case 0:
            mTitle = getString(R.string.title_section1);
            fragment = new ContactsFragment();
            break;
        case 1:
            mTitle = getString(R.string.title_section2);
            fragment = new Fragment();
            break;
        case 2:
            mTitle = getString(R.string.title_section3);
            fragment = new Fragment();
            break;
        }

        if (fragment != null)
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main_activity, menu);
            restoreActionBar();
            return true;
        }
        else
            return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return (item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item));
    }

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

    /**
     * Set the application as default to read sms.
     */
    public void setDefault(View view) {
        Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
        intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, getPackageName());
        startActivity(intent);
    }

    @Override
    public void onContactSelected() {
        startActivity(new Intent(this, ChatActivity.class));
    }
}
