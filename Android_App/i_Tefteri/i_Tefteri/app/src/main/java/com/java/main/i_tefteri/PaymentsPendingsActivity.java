package com.java.main.i_tefteri;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaymentsPendingsActivity extends AppCompatActivity {

    private List<PaymentsPendingsActivity.MyPerson> Persons=new ArrayList<>();
    private ListView PersonList;
    private int SelectItem=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments_pendings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PersonList=(ListView) findViewById(R.id.PersonList);

        fillMyList();

    }

    private void fillMyList(){
        String MyResponse;
        MyMainObject MainObject = (MyMainObject) this.getApplicationContext();
        HttpURLCon Con = new  HttpURLCon();
        Con.SetUrl("http://10.35.251.60:8088/itefteri/Collection/"+MainObject.get_userid());

        Con.sendGet();
        if(Con.GetReturnCode()==200){
            MyResponse=Con.GetReturn();

            try {
                JSONArray jsonarray = new JSONArray(MyResponse);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    //String name = jsonobject.getString("UserID");
                    //String url = jsonobject.getString("url");

                    Persons.add(new PaymentsPendingsActivity.MyPerson(jsonobject.getString("PersonID"),jsonobject.getString("PersonName"),jsonobject.getString("TransDate"),jsonobject.getString("ActualDate"),jsonobject.getString("TransAmount"),jsonobject.getString("DOSEIS"),jsonobject.getString("TransactionID")));




                }

            }
            catch (Exception ex){
                String Error=ex.getMessage();
            }
        }
//        Persons.add(new MyPerson("Χρήστος"));
//        Persons.add(new MyPerson("Ανδρεας"));
//        Persons.add(new MyPerson("Στέργιος"));

        ListAdapter PersonsAdapter = new ListAdapter(this, android.R.layout.simple_list_item_1, Persons);

        PersonList.setAdapter(PersonsAdapter);
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

    private void MyClick(){


        if(SelectItem!=-1){
            MyPerson SelectedPerson=(MyPerson) PersonList.getAdapter().getItem(SelectItem);


                Intent MyIntent=new Intent(this, ActoualPaymentActivity.class);
                MyIntent.putExtra("KEY_PersonName",SelectedPerson.get_PersonName());
                MyIntent.putExtra("KEY_PersonID",SelectedPerson.get_PersonID());
                MyIntent.putExtra("KEY_TransAmount",SelectedPerson.get_TransAmount());
                MyIntent.putExtra("KEY_TransDosis",SelectedPerson.get_TransDoseis());
                MyIntent.putExtra("KEY_TransActualDate",SelectedPerson.get_ActualDate());
                MyIntent.putExtra("KEY_TransactionID",SelectedPerson.get_TransactionID());
                startActivity(MyIntent);
                finish();

            //Intent PersonIntent = new Intent(this, ActoualPaymentActivity.class);
            //startActivity(PersonIntent);
        }


    }

    public class ListAdapter extends ArrayAdapter<MyPerson> {




        public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public ListAdapter(Context context, int resource, List<MyPerson> items) {
            super(context, resource, items);

        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            final int pos=position;
            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.contentpendings, null);
            }

            MyPerson p = getItem(position);


            if (p != null) {


                TextView personname = (TextView) v.findViewById(R.id.PersonName);
                TextView amount = (TextView) v.findViewById(R.id.AmountCollection);
                TextView transdate = (TextView) v.findViewById(R.id.AmountTransDate);
                TextView actualdate = (TextView) v.findViewById(R.id.ActualDate);


                personname.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SelectItem=position;
                        MyClick();
                    }
                });


                if (personname != null) {
                    personname.setText(p.get_PersonName());

                }
                if(amount!=null){
                    amount.setText(p.get_TransAmount());
                }

                if(transdate!=null){
                    transdate.setText(p.get_TransDate());
                }

                if(actualdate!=null){
                    actualdate.setText(p.get_ActualDate());
                }
            }


//            TableLayout tl=(TableLayout) v.findViewById(R.id.MyTable);
//
//            tl.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    MyClick();
//                }
//            });

            return v;




        }




    }



    private class MyPerson{
        private String _PersonID;
        private String _PersonName;
        private String _TransDate;
        private String _ActualDate;
        private String _TransAmount;
        private String _TransDoseis;
        private String _TransactionID;

        public MyPerson(String PersonID,String PersonName,String TransDate,String ActualDate,String TransAmount,String TransDoseis,String TransactionID){
            _PersonID=PersonID;
            _PersonName=PersonName;
            _TransDate=TransDate;
            _ActualDate=ActualDate;
            _TransAmount=TransAmount;
            _TransDoseis=TransDoseis;
            _TransactionID=TransactionID;
        }

        public String get_PersonID(){
            return _PersonID;
        }

        public String get_PersonName(){
            return _PersonName;
        }

        public String get_TransDate(){
            return _TransDate;
        }

        public String get_ActualDate(){
            return _ActualDate;
        }

        public String get_TransAmount(){
            return _TransAmount;
        }

        public String get_TransDoseis(){
            return _TransDoseis;
        }

        public String get_TransactionID(){
            return _TransactionID;
        }

    }

}
