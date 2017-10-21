package com.java.main.i_tefteri;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        Button RegisterButton = (Button) findViewById(R.id.Register_button);
//
//        RegisterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myLogin();
//            }
//        });
    }

    private void myLogin(){
        AutoCompleteTextView mUsernameView = (AutoCompleteTextView) findViewById(R.id.Username);


        EditText mPasswordView = (EditText) findViewById(R.id.password);

        if(mUsernameView.getText().toString().equals("Stergios") && mPasswordView.getText().toString().equals("123")){
            Intent AccountIntent = new Intent(this,AccountActivity.class);
            startActivity(AccountIntent);
            RegisterActivity.this.finish();
        }

    }


}
