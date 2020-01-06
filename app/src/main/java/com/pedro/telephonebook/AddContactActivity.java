package com.pedro.telephonebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

public class AddContactActivity extends AppCompatActivity {

    Toolbar toolbar;
    AppCompatEditText editTextName;
    AppCompatEditText editTextNickName;
    AppCompatEditText editTextTel;
    AppCompatEditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editTextName = findViewById(R.id.edit_txt_name);
        editTextNickName = findViewById(R.id.edit_txt_nickname);
        editTextTel = findViewById(R.id.edit_txt_tel);
        editTextEmail = findViewById(R.id.edit_txt_email);

        toolbar = findViewById(R.id.toolbar_add_contact);
        setSupportActionBar(toolbar);

        View view = toolbar.getChildAt(1);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
