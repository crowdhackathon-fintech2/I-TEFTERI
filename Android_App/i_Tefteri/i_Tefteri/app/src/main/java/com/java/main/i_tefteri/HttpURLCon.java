package com.java.main.i_tefteri;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

import junit.framework.Test;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
//import javax.net.ssl.HttpsURLConnection;

public class HttpURLCon {

    private final String USER_AGENT = "Mozilla/5.0";
    private String _Return;
    private int _ReturnCode;
    private String _Url;
    private String _Body;
    private List<MyHeader> Headers = new ArrayList<>();

    public void set_Body(String value){
        _Body=value;
    }

    public String get_Body(){
        return _Body;
    }

    public void SetUrl(String value) {
        _Url = value;
    }

    public String GetReturn() {
        return _Return;
    }

    public int GetReturnCode() {
        return _ReturnCode;
    }

    public void add_Headers(String id, String value) {
        MyHeader Header = new MyHeader();
        Header.set_id(id);
        Header.set_value(value);

        Headers.add(Header);
        Header = null;
    }
    //url = "http://192.168.1.10:8088/api/Mobile/6945864561";

//    public static void main(String[] args) throws Exception {
//
//        HttpURLConnectionExample http = new HttpURLConnectionExample();
//
//        System.out.println("Testing 1 - Send Http GET request");
//        http.sendGet();
//
//        System.out.println("\nTesting 2 - Send Http POST request");
//        http.sendPost();
//
//    }

    // HTTP GET request
    public void sendGet() {
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }


                @Override
                protected Void doInBackground(Void... params) {
                    //String url = "http://192.168.1.10:8088/api/Mobile/6945864561";
                    try {


                        URL obj = null;
                        obj = new URL(_Url);
                        HttpURLConnection con = null;
                        con = (HttpURLConnection) obj.openConnection();

                        // optional default is GET
                        con.setRequestMethod("GET");


                        //add request header
                        //con.setRequestProperty("id", "6945864561");

                        for (int i = 0; i <= Headers.size() - 1; i++)
                            con.setRequestProperty(Headers.get(i).get_id(), Headers.get(i).get_value());

                        _ReturnCode = con.getResponseCode();
                        //System.out.println("\nSending 'GET' request to URL : " + url);
                        //System.out.println("Response Code : " + responseCode);

                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        //print result
                        //System.out.println(response.toString());
                        _Return = response.toString();
                    } catch (Exception ex) {
                        _Return = ex.getMessage();
                    }
                    return null;
                }
            }.execute().get();
        } catch (Exception ex) {

        }
    }

    // HTTP POST request
    public void sendPut() {
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }


                @Override
                protected Void doInBackground(Void... params) {
                    try {


                        URL url = new URL(_Url);
                        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                        httpCon.setDoOutput(true);
                        httpCon.setRequestMethod("PUT");
                        for (int i = 0; i <= Headers.size() - 1; i++)
                            httpCon.setRequestProperty(Headers.get(i).get_id(), Headers.get(i).get_value());
                        OutputStreamWriter out = new OutputStreamWriter(
                                httpCon.getOutputStream());
                        out.write("Resource content");
                        out.close();
                        httpCon.getInputStream();
                        _ReturnCode= httpCon.getResponseCode();
                        //_Return = String.valueOf(httpCon.getResponseCode());
                    } catch (Exception ex) {
                        _Return = ex.getMessage();
                    } finally {
                        return null;
                    }
                }

            }.execute().get();

        } catch (Exception ex) {

        }
    }

    // HTTP POST request
    public void sendPost(final String UserID,final String User_Name,final String BankID,final String AccountID,final String IBAN_Politi,final String Amount) {
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }


                @Override
                protected Void doInBackground(Void... params) {
                    try {

//                        HttpPost httpPost = new HttpPost(_Url);
//                        httpPost.setEntity(new StringEntity("{\"to\":{\"iban\":\"GR4501101030000010348012377\"},\"charge_policy\":\"OUR\",\"value\":{\"currency\":\"EUR\",\"amount\":1},\"description\":\"tes22t\"}"));

                        HttpPost httpPost = new HttpPost("https://apis.nbg.gr/public/nbgapis/obp/v3.0.1/banks/" + BankID +"/accounts/" + AccountID + "/owner/transaction-request-types/sepa/transaction-requests");
                        httpPost.setEntity(new StringEntity("{\'to\':{\"iban\':\'" + IBAN_Politi + "'\'},\'charge_policy\':\'OUR\',\'value\':{\'" + Amount + "'\':\'EUR\',\'amount\':1},\'description\':\'tes22t\'}"));
                        httpPost.setHeader("Accept", "application/json");
                        httpPost.setHeader("Content-type", "application/json");
                        httpPost.setHeader("sandbox_id", "i_tefteri_sandbox");
                        httpPost.setHeader("application_id", "i_tefteri_application_id");

                        httpPost.setHeader("user_id", UserID);
                        httpPost.setHeader("username", User_Name);
                        httpPost.setHeader("provider_id", "NBG.gr");
                        httpPost.setHeader("provider", "NBG");

                        HttpResponse resp = new DefaultHttpClient().execute(httpPost);
                        _Return=resp.toString();
                        //Log.d("tag",resp.toString());
                    } catch (Exception ex) {
                        _Return = ex.getMessage();
                    } finally {
                        return null;
                    }
                }

            }.execute().get();

        } catch (Exception ex) {

        }
    }

    private class MyHeader {
        private String _id;
        private String _value;

        public void set_id(String value) {
            _id=value;
        }

        public String get_id(){
            return _id;
        }

        public void set_value(String value){
            _value=value;
        }

        public String get_value() {
            return _value;
        }
    }
}