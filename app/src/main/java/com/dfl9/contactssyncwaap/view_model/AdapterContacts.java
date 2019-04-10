package com.dfl9.contactssyncwaap.view_model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dfl9.contactssyncwaap.R;
import com.dfl9.contactssyncwaap.model.Contact;
import java.util.List;

public class AdapterContacts extends BaseAdapter {

    private List<Contact> listContacts;

    public AdapterContacts(List<Contact> listContacts) {
        this.listContacts = listContacts;
    }

    @Override
    public int getCount() {
        return listContacts.size();
    }

    @Override
    public Contact getItem(int position) {
        return listContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater infl= LayoutInflater.from(parent.getContext());
        if (convertView == null){
            convertView = infl.inflate( R.layout.adapter_card ,null);
        }
        TextView name = convertView.findViewById(R.id.lbName);
        TextView tel = convertView.findViewById(R.id.lbCel);

        name.setText(getItem(position).getName());
        tel.setText(getItem(position).getTel());

        return convertView;
    }
}
