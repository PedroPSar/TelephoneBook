package com.pedro.telephonebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.pedro.telephonebook.Control.ContactCtrl;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditContactActivity extends AppCompatActivity {

    private ContactCtrl contactCtrl;

    private Toolbar toolbar;
    private CircleImageView circleImg;
    private AppCompatEditText editTextName;
    private AppCompatEditText editTextNickName;
    private AppCompatEditText editTextTel;
    private AppCompatEditText editTextEmail;
    private ImageView btnAddImg;
    private String imgPath = "";

    private static final int GET_IMAGE = 1;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        contactCtrl = new ContactCtrl();

        circleImg = findViewById(R.id.img_contact);
        editTextName = findViewById(R.id.edit_txt_name);
        editTextNickName = findViewById(R.id.edit_txt_nickname);
        editTextTel = findViewById(R.id.edit_txt_tel);
        editTextEmail = findViewById(R.id.edit_txt_email);
        btnAddImg = findViewById(R.id.btnAddImg);

        loadContentViews();

        toolbar = findViewById(R.id.toolbar_add_contact);
        setSupportActionBar(toolbar);

        View view = toolbar.getChildAt(1);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_add_contact_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.btn_save_contact:

                if(filePath == null){
                    imgPath = ContactCtrl.currentContact.getAvatar();
                }else{
                    imgPath = convertMediaUriToPath(filePath);
                }
                contactCtrl.updateContact(this, imgPath, editTextName, editTextNickName, editTextTel, editTextEmail);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFileChooser(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GET_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GET_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();

            try{
                circleImg.setImageURI(filePath);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String convertMediaUriToPath(Uri uri) {
        String [] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj,  null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }

    private void loadContentViews(){

        circleImg.setImageURI(Uri.parse(ContactCtrl.currentContact.getAvatar()));
        editTextName.setText(ContactCtrl.currentContact.getName());
        editTextNickName.setText(ContactCtrl.currentContact.getNickname());
        editTextTel.setText(ContactCtrl.currentContact.getTel());
        editTextEmail.setText(ContactCtrl.currentContact.getEmail());

    }
}
