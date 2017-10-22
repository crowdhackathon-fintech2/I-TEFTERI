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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

public class ActoualPaymentActivity extends AppCompatActivity {
    private String euro = "\u20ac";
    private DatePicker MyCalendar;

    private String SalerID;
    private String SalerIBAN;
    private TextView TransAmount;
    private String TransactionID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actoual_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView tv = ((TextView)findViewById(R.id.PersonName));
        tv.setText(getIntent().getStringExtra("KEY_PersonName"));

        SalerID=getIntent().getStringExtra("KEY_PersonID");
        SalerIBAN=getIntent().getStringExtra("KEY_AccountID");

        TextView ta = ((TextView)findViewById(R.id.TotalAmount));
        ta.setText("-1300"+euro);

        TransAmount=(TextView) findViewById(R.id.TransAmount);
        TransAmount.setText(getIntent().getStringExtra("KEY_TransAmount"));

        TextView FirstDate = ((TextView)findViewById(R.id.firstdate));
        FirstDate.setText(getIntent().getStringExtra("KEY_TransActualDate"));

        TextView Dosis = ((TextView)findViewById(R.id.installments_count));
        Dosis.setText(getIntent().getStringExtra("KEY_TransDosis"));

        TransactionID=getIntent().getStringExtra("KEY_TransactionID");

//        SimpleDateFormat dateFormat = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            FirstDate.setText(dateFormat.format(new Date())); // it will show 16/07/2013
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            FirstDate.setText(dateFormat.format(new Date()));
//        }
//
////        DatePicker MyCalendar = ((DatePicker) findViewById(R.id.DTPicker));
//
////        FirstDate.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                ShowCalendar();
////            }
////        });
//
//        Spinner periodikotita = (Spinner) findViewById(R.id.periodikotita);
//// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.periodikotita, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        periodikotita.setAdapter(adapter);


        Button BtnApprov=(Button) findViewById(R.id.BtnAprov);

        BtnApprov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApprovPayment();
            }
        });



        Button BtnRefuse=(Button) findViewById(R.id.BtnRefuse);

        BtnRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void ApprovPayment(){
        String IBAN="";
        String MyResponse="";
        EditText Sxolio=(EditText) findViewById(R.id.SxolioPoliti);

        if(Sxolio.getText().equals("σχόλιο αγοραστή")) {
            Toast.makeText(this, "πρέπει να συμπληρώσετε ένα σχόλιο",
                    Toast.LENGTH_LONG).show();
        }
        else{
            MyMainObject MainObject = (MyMainObject) this.getApplicationContext();
            HttpURLCon Con = new  HttpURLCon();
            Con=null;
            Con = new HttpURLCon();
            Con.SetUrl("http://10.35.251.60:8088/itefteri/Users/1");
            Con.add_Headers("Currentuser",MainObject.get_UserName());
            Con.sendGet();
            if (Con.GetReturnCode() == 200) {
                MyResponse = Con.GetReturn();
                try {
                    JSONArray jsonarray = new JSONArray(MyResponse);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);

                        IBAN=jsonobject.getString("BASIKOS_LOGAR");
                    }
                }
                catch(Exception ex){

                }

            }
            Con=null;
            Con = new HttpURLCon();
            Con.sendPost(MainObject.get_userid(), MainObject.get_UserName(),MainObject.get_bank_id(),MainObject.get_account_id(),IBAN,TransAmount.getText().toString());
            Con=null;
            Con = new  HttpURLCon();
            Con.SetUrl("http://10.35.251.60:8088/itefteri/Collection/2");
            Con.add_Headers("Transaction_id",TransactionID);
            Con.add_Headers("SXOLIO_AGORASTH",Sxolio.getText().toString());
            Con.sendPut();
            if(Con.GetReturnCode()==204){
                Toast.makeText(this, "η νέα είσπραξη καταχωρήθηκε με επιτυχία",
                        Toast.LENGTH_LONG).show();
                finish();
            }
            else{
                Toast.makeText(this, "η νέα είσπραξη ΔΕΝ καταχωρήθηκε",
                        Toast.LENGTH_LONG).show();
            }





        }
        //String MyString="";

        //MyMainObject MainObject = (MyMainObject) this.getApplicationContext();

        //MainObject.get


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
