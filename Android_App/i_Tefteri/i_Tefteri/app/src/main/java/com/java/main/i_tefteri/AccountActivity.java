package com.java.main.i_tefteri;

import android.accounts.Account;
import android.app.Activity;
import android.app.LauncherActivity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteTableLockedException;
import android.icu.util.Currency;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccountActivity extends AppCompatActivity {
    private List<MyAccounts> Accounts=new ArrayList<>();
    private ListView AccountList;
    private CheckBox My_Check;
    int SelectedItem=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AccountList = (ListView) findViewById(R.id.AccountList);







        FillAccount2List();

        Button AccountButton = (Button) findViewById(R.id.Account_button);

        AccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySelectAccount();
            }
        });

        Accounts.add(new MyAccounts("1111111111111",100.10));
        Accounts.add(new MyAccounts("2222222222222",200.20));

//        ArrayAdapter<MyAccounts> AccountsAdapter = new ArrayAdapter<MyAccounts>(
//                this,
//                android.R.layout.simple_list_item_checked,
//                Accounts);

        ListAdapter AccountsAdapter = new ListAdapter(this, android.R.layout.simple_list_item_1, Accounts);





        AccountList.setItemsCanFocus(false);
        AccountList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        AccountList.setAdapter(AccountsAdapter);
        AccountList.setItemsCanFocus(false);
        AccountList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);



//        AccountList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(SelectedItem!=-1){
//                    My_Check.setChecked(false);
//
//                    CheckBox My1=(CheckBox) AccountList.getItemAtPosition(SelectedItem);
//
//                    My1.setChecked(true);
//                }
//            }
//        });




//        MyCheck.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
////                buttonView.setChecked(isChecked);
//            }
//
//
//        });




}

//    @Override
//    public void onClick(View v) {
//        /* this returns the checked item positions in the listview*/
//      //ArrayList<MyAccounts> itempositions=.getcheckeditemcount();
//        if(SelectedItem!=-1){
//            My_Check.setChecked(false);
//
//            CheckBox My1=(CheckBox) AccountList.getItemAtPosition(SelectedItem);
//
//            My1.setChecked(true);
//        }


//        for(long i:AccountList.getCheckItemIds())
//        {
//             /* This is the main part...u can retrieve the object in the listview which is checked and do further calculations with it*/
//            AccountList.getId(i)
//
//        }




//    private void CheckTheCorrect(){
//        ((CheckBox) findViewById(R.id.Check)).setChecked(false);
//            for(int i=0; i<=((CheckBox) findViewById(R.id.Check)).length();i++)
//                if(i==SelectedItem){
//                    ((CheckBox) findViewById(R.id.Check)).setChecked(true);
//                }
//
//        }



    private void FillAccount2List(){





//
//        Toast.makeText(this, AccountList.getChoiceMode(),
//                    Toast.LENGTH_LONG).show();
    }




    private void mySelectAccount(){



        if(SelectedItem==-1){
            Toast.makeText(getApplicationContext(),"Θα πρέπει πρώτα να διαλέξετε ένα Λογαριασμό", Toast.LENGTH_SHORT).show();
        }
        else{
            MyAccounts SelectedAccount=(MyAccounts)AccountList.getAdapter().getItem(SelectedItem);
            //Toast.makeText(getApplicationContext(), SelectedAccount.GetAccount_no(), Toast.LENGTH_SHORT).show();

            Intent mainIntent = new Intent(this,MainActivity.class);
            startActivity(mainIntent);
            AccountActivity.this.finish();
        }


        //        //RadioButton Account1=(RadioButton) findViewById(R.id.radio0);
//        //RadioButton Account2=(RadioButton) findViewById(R.id.radio1);
//        String MyAccount_No="";
//        SparseBooleanArray checked = AccountList.getCheckedItemPositions();
//        for (int i = 0; i < AccountList.getAdapter().getCount(); i++) {
//            TableRow MyRow=(TableRow) AccountList.getChildAt(i);
//            CheckBox Check=(CheckBox) MyRow.getChildAt(0);
//            if (Check.isChecked()) {
//                MyAccounts tag = (MyAccounts) AccountList.getItemAtPosition(i);
//
//                String selectedName=tag.GetAccount_no();
//                Toast.makeText(getApplicationContext(), selectedName, Toast.LENGTH_SHORT).show();
//            }
//        }

        //Toast.makeText(this, MyAccount_No,Toast.LENGTH_LONG).show();

//        if(Account1.isChecked()==false && Account2.isChecked()==false){
//            Toast.makeText(this, "Θα πρέπει πρώτα να διαλέξετε ένα Λογαριασμό",
//                    Toast.LENGTH_LONG).show();
//        }
//        else{
//            Intent MainIntent = new Intent(this,MainActivity.class);
//            startActivity(MainIntent);
//            AccountActivity.this.finish();
//        }
    }

    public class ListAdapter extends ArrayAdapter<MyAccounts> {


        Context MyContext;

          public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public ListAdapter(Context context, int resource, List<MyAccounts> items) {
            super(context, resource, items);
            MyContext=context;

           }



//           @Override
//           public View getBind()



        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            String euro = "\u20ac";
            final int pos=position;
            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.contentaccount, null);
            }

            MyAccounts p = getItem(position);


            if (p != null) {


                final CheckBox MyCheck=(CheckBox) v.findViewById(R.id.Check);
                TextView tt1 = (TextView) v.findViewById(R.id.Account_no);
                TextView tt2 = (TextView) v.findViewById(R.id.Balance);
//                TextView tt3 = (TextView) v.findViewById(R.id.description);

                //MyCheck.setChecked(false);


                MyCheck.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                       TableLayout tblGrid = new TableLayout(getBaseContext());
                       //MyCheck.setChecked(false);
                       SelectedItem=position;
                       CheckBox Check2;
//                       for(int i=0;i<=AccountList.getCount()-1;i++){
//                           if(i!=position) {
//                               Check2 = (CheckBox) AccountList.getChildAt(i).findViewById(R.id.Check);
//                               Check2.setChecked(false);
//                               Check2 = null;
//                           }
//                       }
                       //MyCheck.setChecked(false);

                       //for(int i=0;i<=AccountList.getCount()-1;i++){
                           //if(i==position) {
                               //Check2 = (CheckBox) AccountList.getChildAt(0).findViewById(R.id.Check);
                               //Check2.setChecked(false);
                               //Check2 = null;

                           //}
                       //}

                       //CheckBox Check1 =(CheckBox) AccountList.getChildAt(position).findViewById(R.id.Check);
                       //Check1.setChecked(true);


                       //CheckBox chbSeleccion = new CheckBox(getBaseContext());
                       //chbSeleccion.setChecked(false);
                       //for(int i=0;i<=AccountList.getCount();i++){
                       //    CheckBox m1=(CheckBox) MyRow.getChildAt(i).findViewById(R.id.Check);
                       //     m1.setChecked(false);
                       //}
                   }
                   });




//                MyCheck.setOnClickListener(new CheckBox.setOnClickListener() {
//
//
//
//
//                MyCheck.setChecked(false);
//
//                CheckBox My1=(CheckBox) AccountList.getItemAtPosition(SelectedItem);
//
//                My1.setChecked(true);
//
//                ););



                //MyCheck
//                if(SelectedItem!=-1)
//                    MyNotify(SelectedItem);


//                for (int i = 0; i < AccountList.getAdapter().getCount(); i++) {
//                    AccountList.getAdapter().getItem(0);
//
//                }

                if (tt1 != null) {
                    tt1.setText(p.GetAccount_no());
                }

                if (tt2 != null) {
                    tt2.setText(String.valueOf(p.GetAccount_balance())+ euro);
                }

//                if (tt3 != null) {
//                    tt3.setText(p.getDescription());
//                }
            }

//            TableRow MyTable=(TableRow) v.findViewById(R.id.TableRow01);

//            MyCheck.setOnClickListener(new View.OnClickListener() {

//                @Override
//                public void onClick(View v) {
//                    //MyCheck.setChecked(false);
//                    for(int i=0; i < AccountList.getCount(); i++){
////                        if(SelectedItem==i)
////                            AccountList.setItemChecked(i, true);
////                        else
//                            AccountList.setItemChecked(i, false);
//                    }
////                       CheckBox myCheck=(CheckBox) AccountList.getSelectedItem();
////
////                       myCheck.setChecked(true);
//                    //SelectedItem = pos;
//
//                }
//
//
//            });

//            AccountList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> arg0, View v, int position,
//                                        long id) {
//                    for(int i=0; i < AccountList.getCount(); i++){
////                        if(SelectedItem==i)
////                            AccountList.setItemChecked(i, true);
////                        else
//                        AccountList.setItemChecked(i, false);
//                    }
//                }
 //           });
            TableLayout MyTable=(TableLayout) v.findViewById(R.id.MyTable);
            TableRow MyRow1;

            //for(int i=0;i<=MyTable.getChildCount();i++){
                MyRow1=(TableRow) MyTable.getChildAt(0);
                MyRow1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox Check1=(CheckBox) v;
                        Check1.setChecked(false);
                    }
                });
            //}

            TableRow MyRow2;

            //for(int i=0;i<=MyTable.getChildCount();i++){
            MyRow2=(TableRow) MyTable.getChildAt(0);
            MyRow2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox Check1=(CheckBox) v;
                    Check1.setChecked(false);
                }
            });
            //}

            //MyTable.setClickable(true);

//            MyTable.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    for(int i=0;i<= MyTable.getChildCount();i++) {
//                        TableRow MyRow = (TableRow) MyTable.getChildAt(i);
//                        CheckBox Check1 = (CheckBox) MyRow.getChildAt(0);
//                        Check1.setChecked(false);
//                        //TableRow t = (TableRow) View;
//                        //CheckBox Check1=(CheckBox) t.getChildAt(0);
//                        //Check1.setChecked(false);
//                        //TextView firstTextView = (TextView) t.getChildAt(0);
//                        //TextView secondTextView = (TextView) t.getChildAt(1);
//                        //String firstText = firstTextView.getText().toString();
//                        //String secondText = secondTextView.getText().toString();
//                    }
//                        }
//                    });


            return v;

            //final CheckBox MyCheck=(CheckBox) findViewById(R.id.Check);

        }



    }

    private class MyAccounts{

        private String _Account_no;
        private double _Account_balance;


        public MyAccounts(String AccNo,double Balance){
            _Account_no=AccNo;
            _Account_balance=Balance;
        }

        public String GetAccount_no(){
            return _Account_no;
        }

        public void SetAccount_no(String value){
            _Account_no=value;
        }

        public double GetAccount_balance(){
            return _Account_balance;
        }

        public void SetAccount_no(double value){
            _Account_balance=value;
        }

    }

}
