package com.pedro.telephonebook.Control;

import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;

import androidx.appcompat.widget.AppCompatEditText;
import com.pedro.telephonebook.DataBase.ContactsDAO;
import com.pedro.telephonebook.EditContactActivity;
import com.pedro.telephonebook.Models.Contact;

import java.util.List;

public class ContactCtrl {

    public static final int ITEM_EDIT_ID = 121;
    public static final int ITEM_DELETE_ID = 122;
    public static Contact currentContact;
    private static final String REGULAR_TEL_NUMBER = "\\([1-9]\\d\\)\\s9\\d{4}\\s-\\s\\d{4}";

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

    public void editContact(Context context, int id){
        ContactsDAO dao = new ContactsDAO(context);

        List<Contact> contacts = dao.loadContacts();

        if(contacts != null){

            currentContact = new Contact();
            currentContact.set_id(contacts.get(id).get_id());
            currentContact.setAvatar(contacts.get(id).getAvatar());
            currentContact.setName(contacts.get(id).getName());
            currentContact.setNickname(contacts.get(id).getNickname());
            currentContact.setTel(contacts.get(id).getTel());
            currentContact.setEmail(contacts.get(id).getEmail());

            if(currentContact != null){
                Intent intent = new Intent(context, EditContactActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

        }

    }

    public void updateContact(Context context, String avatar, AppCompatEditText name, AppCompatEditText nickName, AppCompatEditText tel, AppCompatEditText email){
        ContactsDAO dao = new ContactsDAO(context);

        Contact contact = new Contact();
        contact.setAvatar(avatar);
        contact.setName(name.getText().toString());
        contact.setNickname(nickName.getText().toString());
        contact.setTel(tel.getText().toString());
        contact.setEmail(email.getText().toString());

        dao.updateContact(contact, currentContact);
    }

    public boolean checkFormat(String number){
        return number.matches(REGULAR_TEL_NUMBER);
    }

    public boolean checkEmailFormat(String email){
        return email.contains("@");
    }

    public String formatMobileNumber(String string){

        String formatted = "";

        if(string != ""){

            if(string.length() == 1 && !string.contains("(")){
                formatted = "(" + string;

            }else if(string.length() == 3 && !string.contains(")")){
                formatted = string + ") ";

            }else if(string.length() == 10){
                formatted = string + " - ";

            }else if(string.length() == 5 && string.contains(")")){
                formatted = string.substring(0, 4) + " " + string.substring(4);

            }else if(string.length() == 4 && !string.contains(")")){
                formatted = string.substring(0, 3) + ") " + string.substring(3);

            }else if(string.length() == 11 && !string.contains(" - ")){
                formatted = string.substring(0, 10) + " - " + string.substring(10);

            }else if(string.length() == 12 && !string.contains("-")){
                formatted = string.substring(0, 10) + " - " + string.substring(11);

            }else if(string.length() == 13 && !string.contains("- ")){
                formatted = string.substring(0, 12) + " " + string.substring(12);

            }else{
                formatted = string;
            }

        }
        return formatted;
    }

}
