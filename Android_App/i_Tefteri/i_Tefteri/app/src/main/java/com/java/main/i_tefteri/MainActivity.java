package com.java.main.i_tefteri;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View.OnLongClickListener;
import android.view.View.OnClickListener;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ImageView mainImage;
    private NavigationView navigationView;
    private static final int SELECT_PICTURE = 0;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        Button NewPayment=(Button) findViewById(R.id.BtnPayments);

        NewPayment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                NewPayment();

            }
        });


        Button NewCollect=(Button) findViewById(R.id.BtnCollection);

        NewCollect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                NewCollect();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView= (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainImage = (ImageView) findViewById(R.id.PersonImage);
                mainImage.setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //Toast.makeText(MainActivity.this, "Test",Toast.LENGTH_LONG).show();
                        FingerHold();
                        return true;
                    }

//                    //@Override
//                    public boolean onClick(View v) {
//                        Toast.makeText(MainActivity.this, "Test",Toast.LENGTH_LONG).show();
//                        return true;
//                    }
                });
            }
        }, 1000);


//        ImageView mainImage = (ImageView) findViewById(R.id.PersonImage);
//        mainImage.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Test",Toast.LENGTH_LONG).show();
//
//            }
//        });

//        navigationView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavMenuClick();
//            }
//        });



//        if(isFirstTime()){
//            Intent RegisterIntent = new Intent(this,RegisterActivity.class);
//            startActivity(RegisterIntent);
//        }

        MyMainObject MainObject = (MyMainObject) this.getApplicationContext();


    }


    private void NewPayment(){
        Intent NewPaymentsIntent = new Intent(this, PaymentsActivity.class);
        startActivity(NewPaymentsIntent);
    }

    private void NewCollect(){
        Intent NewCollectIntent = new Intent(this, CollectMenuActivity.class);
        startActivity(NewCollectIntent);
    }

    private boolean is4Register(){
        String Response;
        String Url="http://192.168.1.10:8088/api/Mobile/";
        HttpURLCon Con = new  HttpURLCon();
        try {

            String Mobile_number ="6945864561";
            Con.SetUrl(Url + Mobile_number);
        }
        catch(Exception ex){
            Toast.makeText(this, "Error: " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        try {
            Con.sendGet();
            if(Con.GetReturnCode()==200){
                JSONObject obj = new JSONObject(Con.GetReturn());
                String pageName = obj.getString("Mobile_Num");

//                JSONArray arr = obj.getJSONArray("posts");
//                for (int i = 0; i < arr.length(); i++)
//                {
//                    String post_id = arr.getJSONObject(i).getString("post_id");
//                }
//                Toast.makeText(this, pageName,
//                        Toast.LENGTH_LONG).show();
                if(pageName==null){
                    Con=null;
                    return false;
                }
                else {
                    Con=null;
                    return true;
                }
            }
            else{
                return false;
            }
        }
        catch(Exception ex){
            Toast.makeText(this, "Error: " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            return false;
        }
        finally {
            return true;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            Bitmap bitmap = getPath(data.getData());
//            mainImage.setImageBitmap(bitmap);
//        }
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            //ImageView imageView = (ImageView) findViewById(R.id.PersonImage);
            //BitmapFactory.Options options;
            //options = new BitmapFactory.Options();
            //options.inSampleSize =8;
            //String fname=new File(picturePath, MyArray[MyArray.length]).getAbsolutePath();
            InputStream stream = null;
            try {
                String[] myArray;
                myArray= picturePath.split("/");
                stream = new FileInputStream(picturePath);
                byte[] MyImage=convertStreamToString(stream);
                String FileType=myArray[myArray.length].substring(myArray[myArray.length].length()-3);



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);

            mainImage.setImageBitmap(bitmap);
//            imageView.refreshDrawableState();
//            navigationView.refreshDrawableState();

        }
    }

    private byte[] convertStreamToString(InputStream is)
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        try {
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            buffer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toByteArray();
    }

    private Bitmap getPath(Uri uri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        //String filePath = uri.toString();
        cursor.close();
        // Convert file path into bitmap image using below line.
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        return bitmap;
    }

    private void FingerHold(){
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    private void NavMenuClick(){
        mainImage = (ImageView) findViewById(R.id.PersonImage);
        mainImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Test",Toast.LENGTH_LONG).show();

            }
        });
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_button1) {
            // Handle the camera action
        } else if (id == R.id.nav_button2) {

        } else if (id == R.id.nav_button3) {

        } else if (id == R.id.nav_button4) {

        } else if (id == R.id.nav_button5) {

        //} else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class Person{
        public String Mobile_Num;
        public String Surname;
        public String Name;
        public byte[] PersonImage;
        public String PersonImageType;
    }
}

