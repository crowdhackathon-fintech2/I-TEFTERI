package com.java.main.i_tefteri;

import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;

public class newPaymentSelectPersonActivity extends AppCompatActivity {

    private List<MyPerson> Persons=new ArrayList<>();
    private ListView PersonList;
    private int SelectItem=-1;
    private String WhoCalls="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_payment_select_person);
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
        PersonList = (ListView) findViewById(R.id.PersonList);
        fillMyList();

        WhoCalls=getIntent().getStringExtra("KEY_Whois");


    }

    private void MyClick(){


        if(SelectItem!=-1){
            MyPerson SelectedPerson=(MyPerson) PersonList.getAdapter().getItem(SelectItem);

            if(WhoCalls.equals("Payment")){
                Intent MyIntent=new Intent(newPaymentSelectPersonActivity.this, ActoualPaymentActivity.class);
                MyIntent.putExtra("KEY_PersonName",SelectedPerson.GetPersonName());
                MyIntent.putExtra("KEY_PersonID",SelectedPerson.GetPersonID());
                MyIntent.putExtra("KEY_AccountID",SelectedPerson.GetPersonAccountID());
                startActivity(MyIntent);
            }
            else if(WhoCalls.equals("Collection")){
                Intent MyIntent=new Intent(newPaymentSelectPersonActivity.this, ActualCollectionActivity.class);
                MyIntent.putExtra("KEY_PersonName",SelectedPerson.GetPersonName());
                MyIntent.putExtra("KEY_PersonID",SelectedPerson.GetPersonID());
                MyIntent.putExtra("KEY_AccountID",SelectedPerson.GetPersonAccountID());
                startActivity(MyIntent);
            }

            //Intent PersonIntent = new Intent(this, ActoualPaymentActivity.class);
            //startActivity(PersonIntent);
        }


    }

    private void fillMyList(){
        String MyResponse;
        HttpURLCon Con = new  HttpURLCon();
        Con.SetUrl("http://10.35.251.60:8088/itefteri/Users/-1");
        Con.add_Headers("user","test");
        Con.sendGet();
        if(Con.GetReturnCode()==200){
            MyResponse=Con.GetReturn();

            try {
                JSONArray jsonarray = new JSONArray(MyResponse);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    //String name = jsonobject.getString("UserID");
                    //String url = jsonobject.getString("url");
                    MyMainObject MainObject = (MyMainObject) this.getApplicationContext();
                    if(MainObject.get_IBAN().equals(jsonobject.getString("BASIKOS_LOGAR"))){

                    }
                    else{
                        Persons.add(new MyPerson(jsonobject.getString("UserID"),jsonobject.getString("BASIKOS_LOGAR"),jsonobject.getString("iBankuserID")));
                    }



                }
//                JSONObject obj = new JSONObject(MyResponse.substring(1,MyResponse.length()-1));
//
////                JSONArray arr = obj.getJSONArray("");
//                String UserID1;
//                String UserID2;
//                UserID1=obj.getString("UserID");
//                obj = new JSONObject(MyResponse.substring(1,MyResponse.length()-1));
//                UserID2=obj.getString("UserID");
//                UserID2=obj.getString("UserID");
//                    for (int i = 0; i < arr.length(); i++)
//                         UserID1= arr.getJSONObject(i).getString("UserID");
                //String UserID1=obj.getJSONObject("").getString("UserID");
                //String pageName = obj.getJSONObject("pageInfo").getString("pageName");



//                String Account_ID = obj.getString("id");
//                String Bank_ID = obj.getString("bank_id");
//                String IBAN = "";
//                MyMainObject MainObject = ((MyMainObject) this.getApplication());
//
//
//                MainObject.set_account_id(Account_ID);
//                MainObject.set_bank_id(Bank_ID);
//                JSONArray arr = obj.getJSONArray("account_routing");
//                for (int i = 0; i < arr.length(); i++) {
//                    IBAN = arr.getJSONObject(i).getString("address");
//
//                }
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

    public class ListAdapter extends ArrayAdapter<MyPerson> {


        Context MyContext;

        public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public ListAdapter(Context context, int resource, List<MyPerson> items) {
            super(context, resource, items);
            MyContext=context;

        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            final int pos=position;
            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.contentpaymentnewperson, null);
            }

            MyPerson p = getItem(position);


            if (p != null) {


                TextView tt1 = (TextView) v.findViewById(R.id.Person);
                TextView PersonID = (TextView) v.findViewById(R.id.PersonID);
                TextView PersonAccountID = (TextView) v.findViewById(R.id.AccountID);


                tt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SelectItem=position;
                        MyClick();
                    }
                });


                if (tt1 != null) {
                    tt1.setText(p.GetPersonName());
                }
                if(PersonID!=null){
                    PersonID.setText(p.GetPersonID());
                }

                if(PersonAccountID!=null){
                    PersonAccountID.setText(p.GetPersonAccountID());
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
    private String _PersonAccountID;
    private String _PersonName;

    public MyPerson(String PersonID,String PersonAccountID,String PersonName){
        _PersonID=PersonID;
        _PersonName=PersonName;
        _PersonAccountID=PersonAccountID;
    }

    public String GetPersonID(){
        return _PersonID;
    }
    public String GetPersonAccountID(){
        return _PersonAccountID;
    }
    public String GetPersonName(){
        return _PersonName;
    }

    public void SetPerson(String value){
        _PersonName=value;
    }
}
}
