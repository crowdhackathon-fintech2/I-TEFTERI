package com.java.main.i_tefteri;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.widget.Toast;
import android.util.Log;
import org.json.*;


//import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class SingInActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private EditText UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        // Set up the login form.

        getWindow().setBackgroundDrawableResource(R.drawable.splashclear);

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.Username);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);

        UserName = (EditText) findViewById(R.id.Username);


        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });



        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
//        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        }
//        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
//            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
//                    .setAction(android.R.string.ok, new View.OnClickListener() {
//                        @Override
//                        @TargetApi(Build.VERSION_CODES.M)
//                        public void onClick(View v) {
//                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//                        }
//                    });
//        } else {
//            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        String MyResponse;

        HttpURLCon Con = new  HttpURLCon();
        Con.SetUrl("https://apis.nbg.gr/public/nbgapis/obp/v3.0.1/my/accounts");
        Con.add_Headers("application_id","i_tefteri_application_id");
        Con.add_Headers("provider","NBG");
        Con.add_Headers("provider_id","NBG.gr");
        Con.add_Headers("sandbox_id","i_tefteri_sandbox");
        if(UserName.getText().toString().equals("User1"))
            Con.add_Headers("user_id","c060ae72-994c-40b5-82eb-0403d50f8600");
        else
            Con.add_Headers("user_id","4c7a70df-5a9c-4cc4-821b-32a95355b396");
        Con.add_Headers("username",UserName.getText().toString());
        Con.sendGet();

        if(Con.GetReturnCode()==200){

            showProgress(true);

            MyResponse=Con.GetReturn();
            try{
                JSONObject obj = new JSONObject(MyResponse.substring(1,MyResponse.length()-1));

                //String pageName = obj.getJSONObject("pageInfo").getString("pageName");


                String Account_ID=obj.getString("id");
                String Bank_ID=obj.getString("bank_id");
                String IBAN="";
                MyMainObject MainObject = ((MyMainObject) this.getApplication());

                MainObject.setUserName(UserName.getText().toString());

                MainObject.set_account_id(Account_ID);
                MainObject.set_bank_id(Bank_ID);
                JSONArray arr = obj.getJSONArray("account_routing");
                for (int i = 0; i < arr.length(); i++)
                {
                    IBAN = arr.getJSONObject(i).getString("address");

                }

                MainObject.set_IBAN(IBAN);
//                Con=null;
//                Con = new  HttpURLCon();
//
                Con.SetUrl("https://apis.nbg.gr/public/nbgapis/obp/v3.0.1/my/banks/" + MainObject.get_bank_id() +"/accounts/" + MainObject.get_account_id() +"/account");
//                Con.add_Headers("application_id","i_tefteri_application_id");
//                Con.add_Headers("provider","NBG");
//                Con.add_Headers("provider_id","NBG.gr");
//                Con.add_Headers("sandbox_id","i_tefteri_sandbox");
//                Con.add_Headers("user_id","c060ae72-994c-40b5-82eb-0403d50f8600");
//                Con.add_Headers("username",UserName.getText().toString());
                Con.sendGet();
                MyResponse=Con.GetReturn();
                if(Con.GetReturnCode()==200) {
                    if (MyResponse.substring(0, 1) == "[")
                        obj = new JSONObject(MyResponse.substring(1, MyResponse.length() - 1));
                    else
                        obj = new JSONObject(MyResponse);

                    arr = null;
                    arr = obj.getJSONArray("owners");
                    for (int i = 0; i < arr.length(); i++) {
                        MainObject.set_Owner_Name(arr.getJSONObject(i).getString("display_name"));


                    }

                    arr = null;

//                    arr = obj.getJSONArray("balance");
//                    for (int i = 0; i < arr.length(); i++)
//                    {
//                        MainObject.set_Amount(Double.parseDouble((arr.getJSONObject(i).getString("amount"))));
//
//                    }

                    MainObject.set_Amount(Double.parseDouble(obj.getJSONObject("balance").getString("amount")));


                    Con=null;
                    Con = new HttpURLCon();
                    Con.SetUrl("http://10.35.251.60:8088/itefteri/Users/1");
                    Con.add_Headers("Currentuser",UserName.getText().toString());
                    Con.sendGet();
                    if (Con.GetReturnCode() == 200) {
                        MyResponse = Con.GetReturn();
                        JSONArray jsonarray = new JSONArray(MyResponse);
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);

                            MainObject.set_userid(jsonobject.getString("UserID"));
                        }


                    }
                }




            }
            catch(Exception ex){
                String Error=ex.getMessage();
            }

            if(isFirstTime()){
                Intent RegisterPhoneIntent = new Intent(this,RegisterPhoneActivity.class);
                startActivity(RegisterPhoneIntent);
                SingInActivity.this.finish();
            }else{
                Intent mainIntent = new Intent(this,MainActivity.class);
                startActivity(mainIntent);
                SingInActivity.this.finish();
            }
        }









    }

    private boolean isFirstTime(){
        Boolean isFirst=true;
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        isFirst=sharedPreferences.getBoolean("isFirstTime",true);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirstTime", false);
        editor.commit();

        return false;//isFirst;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(SingInActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mUsernameView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

