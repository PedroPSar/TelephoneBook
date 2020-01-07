package com.pedro.telephonebook.Control;

import android.content.Context;

import androidx.appcompat.widget.AppCompatEditText;

import com.pedro.telephonebook.DataBase.ContactsDAO;
import com.pedro.telephonebook.Models.Contact;

public class ContactCtrl {

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

}
