package com.nullpoint.mensajero;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Contact> mContactList;

    public ContactListAdapter(Context context) {
        mContext = context;
        mContactList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Contact tmp = new Contact();

            tmp.setName("Name " + (i+1));
            tmp.setLastSms("Last message...");

            mContactList.add(tmp);
        }
    }

    @Override
    public int getCount() {
        return mContactList.size();
    }

    @Override
    public Object getItem(int position) {
        return mContactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.contact_item_layout, null);
        }

        Contact contact = mContactList.get(position);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView lastSms = (TextView) view.findViewById(R.id.last_sms);

        name.setText(contact.getName());
        lastSms.setText(contact.getLastSms());

        return view;
    }

    public class Contact {
        private String mName;
        private String mLastSms;

        public Contact() {
        }

        public String getName() {
            return mName;
        }

        public String getLastSms() {
            return mLastSms;
        }

        public void setName(String name) {
            mName = name;
        }

        public void setLastSms(String lastSms) {
            mLastSms = lastSms;
        }
    }
}