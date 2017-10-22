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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class ActualCollectionActivity extends AppCompatActivity {
    private String euro = "\u20ac";
    private EditText TransAmount;

    private String ByerID;
    private String ByerIBAN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_collection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tv = ((TextView)findViewById(R.id.PersonName));
        tv.setText(getIntent().getStringExtra("KEY_PersonName"));
        CalcTotalAmount();

        ByerID=getIntent().getStringExtra("KEY_PersonID");
        ByerIBAN=getIntent().getStringExtra("KEY_AccountID");

        TextView FirstDate = ((TextView) findViewById(R.id.firstdate));
//        FirstDate.setText(getIntent().getStringExtra("KEY_TransActualDate"));

        TransAmount=(EditText) findViewById(R.id.TransAmount);

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

        Spinner periodikotita = (Spinner) findViewById(R.id.periodikotita);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.periodikotita, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        periodikotita.setAdapter(adapter);

//        TransAmount=(EditText) findViewById(R.id.TransAmount);
//        TransAmount.setText(getIntent().getStringExtra("KEY_TransActualDate"));


        Button BtnRefuse=(Button) findViewById(R.id.BtnRefuse);

        BtnRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Button BtnAprov=(Button) findViewById(R.id.BtnAprov);

        BtnAprov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateCollection();
            }
        });

    }

    private void CreateCollection(){

        EditText Sxolio=(EditText) findViewById(R.id.SxolioPoliti);
        EditText Doseis=(EditText) findViewById(R.id.installments_count);
        EditText FirstDoshDate=(EditText) findViewById(R.id.firstdate);
        Spinner periodikotita=(Spinner) findViewById(R.id.periodikotita);



        if(TransAmount.getText().equals("0")){
            Toast.makeText(this, "το ποσό δεν μπορεί να έιναι μηδενικό",
                        Toast.LENGTH_LONG).show();
        }else if(Sxolio.getText().equals("σχόλιο πωλητή")){
            Toast.makeText(this, "πρέπει να συμπληρώσετε ένα σχόλιο",
                    Toast.LENGTH_LONG).show();
        }else if(Integer.parseInt(Doseis.getText().toString())>1 && periodikotita.getSelectedItem().equals("μια φορά")){
            Toast.makeText(this, "η περιοδικότητα είναι λάθος",
                    Toast.LENGTH_LONG).show();
        }
        else{
            MyMainObject MainObject = (MyMainObject) this.getApplicationContext();
            HttpURLCon Con = new  HttpURLCon();
            Con.SetUrl("http://10.35.251.60:8088/itefteri/Collection/1");
            Con.add_Headers("userID_AGORASTH",ByerID);
            Con.add_Headers("userID_PWLHTH",MainObject.get_userid());
            Con.add_Headers("SXOLIO_PWLHTH",Sxolio.getText().toString());
            Con.add_Headers("POSO",TransAmount.getText().toString());
            Con.add_Headers("DOSEIS",Doseis.getText().toString());
            Con.add_Headers("HMEROMHNIA_EKT",FirstDoshDate.getText().toString());
            Con.add_Headers("Peridikotita",periodikotita.getSelectedItem().toString());



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

    }


    private void CalcTotalAmount(){
        TextView ta = ((TextView) findViewById(R.id.TotalAmount));
        ta.setText("-1300" + euro);
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
