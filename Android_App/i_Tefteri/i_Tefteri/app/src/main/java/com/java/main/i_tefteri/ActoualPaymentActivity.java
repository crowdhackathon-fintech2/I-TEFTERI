package com.java.main.i_tefteri;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

public class ActoualPaymentActivity extends AppCompatActivity {
    private String euro = "\u20ac";
    private DatePicker MyCalendar;

    private String SalerID;
    private String SalerIBAN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actoual_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView tv = ((TextView)findViewById(R.id.PersonName));
        tv.setText(getIntent().getStringExtra("KEY_PersonName"));

        SalerID=getIntent().getStringExtra("KEY_PersonID");
        SalerIBAN=getIntent().getStringExtra("KEY_AccountID ");

        TextView ta = ((TextView)findViewById(R.id.TotalAmount));
        ta.setText("-1300"+euro);

        TextView FirstDate = ((TextView)findViewById(R.id.firstdate));
        SimpleDateFormat dateFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FirstDate.setText(dateFormat.format(new Date())); // it will show 16/07/2013
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FirstDate.setText(dateFormat.format(new Date()));
        }

//        DatePicker MyCalendar = ((DatePicker) findViewById(R.id.DTPicker));

//        FirstDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ShowCalendar();
//            }
//        });

        Spinner periodikotita = (Spinner) findViewById(R.id.periodikotita);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.periodikotita, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        periodikotita.setAdapter(adapter);


        Button BtnApprov=(Button) findViewById(R.id.BtnAprov);

        BtnApprov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApprovPayment();
            }
        });
    }


    private void ApprovPayment(){


        HttpURLCon Con = new  HttpURLCon();
        Con.SetUrl("https://apis.nbg.gr/public/nbgapis/obp/v3.0.1/sandbox/"+ "i_tefteri_sandbox" +"/users");
        Con.add_Headers("application_id","i_tefteri_application_id");
        Con.add_Headers("provider","NBG");
        Con.add_Headers("provider_id","NBG.gr");
        Con.add_Headers("sandbox_id","i_tefteri_sandbox");
        Con.add_Headers("user_id","c060ae72-994c-40b5-82eb-0403d50f8600");
        Con.add_Headers("username","User1");

        Con.sendPost();


    }


    private void ShowCalendar(){
        MyCalendar.setVisibility(View.VISIBLE);
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
