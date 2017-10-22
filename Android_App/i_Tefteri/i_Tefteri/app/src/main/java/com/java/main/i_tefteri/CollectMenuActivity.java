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

public class CollectMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button BtnNewCollection=(Button) findViewById(R.id.BtnNewCollect1);

        BtnNewCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPerson();
            }
        });



    }

    private void SelectPerson(){
        Intent MyIntent=new Intent(CollectMenuActivity.this, newPaymentSelectPersonActivity.class);
        MyIntent.putExtra("KEY_Whois","Collection");
        startActivity(MyIntent);
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
