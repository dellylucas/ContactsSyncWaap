package com.dfl9.contactssyncwaap;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.dfl9.contactssyncwaap.model.Contact;
import com.dfl9.contactssyncwaap.view_model.AdapterContacts;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private ListView listaItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Contact> contactos =  getContactNumbers();


        listaItem= findViewById(R.id.listItems);
        AdapterContacts adapter = new AdapterContacts(contactos);

        listaItem.setAdapter(adapter);

    }
    private  List<Contact>  getContactNumbers(){
        List<Contact>  contactData = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor contactCursor = cr.query(ContactsContract.RawContacts.CONTENT_URI, new String[]{ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY,ContactsContract.RawContacts.CONTACT_ID, ContactsContract.RawContacts.ACCOUNT_TYPE}, ContactsContract.RawContacts.ACCOUNT_TYPE+ " = ?", new String[]{"com.whatsapp"}, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY);
    /*    ArrayList<String> wwaa = new ArrayList<>();
        contactCursor.moveToNext();
        do {
            String contactId = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY));
            String contactIda = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_TYPE));
            wwaa.add(contactId+"  -- "+contactIda);

        } while (contactCursor.moveToNext());
        contactCursor.close();*/

        if (contactCursor != null) {
            if (contactCursor.getCount() > 0) {
                if (contactCursor.moveToFirst()) {
                    do {
                        String contactId = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.RawContacts.CONTACT_ID));
                        if (contactId != null) {
                            Cursor c = cr.query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    new String[]{ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                                            ContactsContract.CommonDataKinds.Phone.NUMBER,
                                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.STATUS},
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",  new String[]{contactId}, null);

                            if (c != null) {
                                c.moveToFirst();
                                String dataName = "";
                                String dataCel = "";
                                dataName = c.getString(2);
                                dataCel = c.getString(1);
                                c.close();
                                contactData.add(new Contact(dataName,dataCel));

                            }
                        }
                    } while (contactCursor.moveToNext());
                    contactCursor.close();
                }
            }
        }

        return contactData;
    }
}
