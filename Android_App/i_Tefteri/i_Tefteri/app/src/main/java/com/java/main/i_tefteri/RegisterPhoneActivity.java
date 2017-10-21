package com.java.main.i_tefteri;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPhoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button RegisterButton = (Button) findViewById(R.id.btnRegisterPhone);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePhoneAndContinue();
            }
        });


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


    private void SavePhoneAndContinue(){
//        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        String Mobile_number = tm.getLine1Number();
        String Mobile_number = "6945864561";

        EditText MyPhone=(EditText) findViewById(R.id.phone_num);

        if(MyPhone.getText().toString().equals(Mobile_number)){
            Intent AccountIntent = new Intent(this,AccountActivity.class);
            startActivity(AccountIntent);
            RegisterPhoneActivity.this.finish();
        }else
            Toast.makeText(this, "Λάθος Αριθμός Τηλεφώνου",
                        Toast.LENGTH_LONG).show();


    }


}
