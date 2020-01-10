package com.pedro.telephonebook.Control;

import android.content.Context;

import androidx.appcompat.widget.AppCompatEditText;

import com.pedro.telephonebook.DataBase.ContactsDAO;
import com.pedro.telephonebook.Models.Contact;

import java.util.List;

public class ContactCtrl {

    public static final int ITEM_EDIT_ID = 121;
    public static final int ITEM_DELETE_ID = 122;

    public void addContact(Context context, String avatar, AppCompatEditText name, AppCompatEditText nickName, AppCompatEditText tel, AppCompatEditText email){
        ContactsDAO dao = new ContactsDAO(context);

        Contact contact = new Contact();
        contact.setAvatar(avatar);
        contact.setName(name.getText().toString());
        contact.setNickname(nickName.getText().toString());
        contact.setTel(tel.getText().toString());
        contact.setEmail(email.getText().toString());

        dao.insertContact(contact);
    }

    public void deleteContact(Context context, int id){
        ContactsDAO dao = new ContactsDAO(context);

        List<Contact> contacts = dao.loadContacts();

        Contact contact = new Contact();
        contact.set_id(contacts.get(id).get_id());
        contact.setAvatar(contacts.get(id).getAvatar());
        contact.setName(contacts.get(id).getName());
        contact.setNickname(contacts.get(id).getNickname());
        contact.setTel(contacts.get(id).getTel());
        contact.setEmail(contacts.get(id).getEmail());

        dao.deleteContact(contact);
    }

}
