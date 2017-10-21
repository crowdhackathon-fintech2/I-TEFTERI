package com.java.main.i_tefteri;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class PaymentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
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

        Button SelectPerson=(Button) findViewById(R.id.BtnNewPayment1);

        SelectPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Select_Person();

            }
        });

    }

    private void Select_Person(){
        Intent SelectPersonIntent = new Intent(this, newPaymentSelectPersonActivity.class);
        startActivity(SelectPersonIntent);
        //startActivity(new Intent(PaymentsActivity.this, ActoualPaymentActivity.class).putExtra("KEY_PersonName",Person.GetPersonName()));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
