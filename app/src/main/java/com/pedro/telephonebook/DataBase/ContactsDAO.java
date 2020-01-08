package com.pedro.telephonebook.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.pedro.telephonebook.Models.Contact;
import com.pedro.telephonebook.R;

import java.util.ArrayList;
import java.util.List;

public class ContactsDAO {

    private SQLiteDatabase db;
    private Context context;

    public ContactsDAO(Context context){
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();

        this.context = context;
    }

    public void insertContact(Contact contact){

        if(contact != null){
            ContentValues values = new ContentValues();
            values.put(DataBaseHelper.COLUMN_AVATAR, contact.getAvatar());
            values.put(DataBaseHelper.COLUMN_NAME, contact.getName());
            values.put(DataBaseHelper.COLUMN_NICKNAME, contact.getNickname());
            values.put(DataBaseHelper.COLUMN_TEL, contact.getTel());
            values.put(DataBaseHelper.COLUMN_EMAIL, contact.getEmail());

            db.insert(DataBaseHelper.TABLE_NAME, null, values);
            Toast.makeText(context, context.getString(R.string.toast_text_insert_contact), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteContact(Contact contact){

        if(contact != null){
            db.delete(DataBaseHelper.TABLE_NAME, "_id = ?", new String[]{"" + contact.get_id()});
            Toast.makeText(context, context.getString(R.string.toast_text_delete_contact), Toast.LENGTH_SHORT).show();
        }
    }

    public void updateContact(Contact editedContact, Contact contact){

        if(contact != null) {
            ContentValues values = new ContentValues();
            values.put(DataBaseHelper.COLUMN_AVATAR, editedContact.getAvatar());
            values.put(DataBaseHelper.COLUMN_AVATAR, editedContact.getAvatar());
            values.put(DataBaseHelper.COLUMN_NAME, editedContact.getName());
            values.put(DataBaseHelper.COLUMN_NICKNAME, editedContact.getNickname());
            values.put(DataBaseHelper.COLUMN_TEL, editedContact.getTel());
            values.put(DataBaseHelper.COLUMN_EMAIL, editedContact.getEmail());

            db.update(DataBaseHelper.TABLE_NAME, values, DataBaseHelper.COLUMN_NAME + " = ?", new String[]{"" + contact.getName()});
            Toast.makeText(context, context.getString(R.string.toast_text_update_contact), Toast.LENGTH_SHORT).show();
        }
    }

    public List<Contact> loadContacts(){
        List<Contact> contactList = new ArrayList<>();
        String[]columns = new String[]{DataBaseHelper.COLUMN_ID, DataBaseHelper.COLUMN_AVATAR,
        DataBaseHelper.COLUMN_NAME, DataBaseHelper.COLUMN_NICKNAME, DataBaseHelper.COLUMN_TEL, DataBaseHelper.COLUMN_EMAIL};

        Cursor cursor = db.query(DataBaseHelper.TABLE_NAME, columns, null,
                null, null, null, DataBaseHelper.COLUMN_NAME + " ASC");

        try{

            if(cursor.getCount() > 0){
                cursor.moveToFirst();

                do{

                    Contact contact = new Contact();
                    contact.set_id(cursor.getInt(0));
                    contact.setAvatar(cursor.getString(1));
                    contact.setName(cursor.getString(2));
                    contact.setNickname(cursor.getString(3));
                    contact.setTel(cursor.getString(4));
                    contact.setEmail(cursor.getString(5));

                    contactList.add(contact);

                }while (cursor.moveToNext());
            }

        } catch (Exception e){
            Toast.makeText(context, context.getString(R.string.toast_text_load_error), Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor != null){
                cursor.close();
            }
        }

        return contactList;
    }

    public List<Contact> queryContact(String query){
        List<Contact> contactList = new ArrayList<>();

        Cursor cursor = db.query(DataBaseHelper.TABLE_NAME, null, null,
                null, null, null, DataBaseHelper.COLUMN_NAME + " DESC");

        try{

            if(cursor.getCount() > 0){
                cursor.moveToFirst();

                do{

                    if(cursor.getString(1).contains(query)){
                        Contact contact = new Contact();
                        contact.set_id(cursor.getInt(0));
                        contact.setAvatar(cursor.getString(1));
                        contact.setName(cursor.getString(2));
                        contact.setNickname(cursor.getString(3));
                        contact.setTel(cursor.getString(4));
                        contact.setEmail(cursor.getString(5));

                        contactList.add(contact);
                    }

                }while (cursor.moveToNext());
            }

        } catch (Exception e){
            Toast.makeText(context, context.getString(R.string.toast_text_update_error), Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor != null){
                cursor.close();
            }
        }

        return contactList;
    }
}
