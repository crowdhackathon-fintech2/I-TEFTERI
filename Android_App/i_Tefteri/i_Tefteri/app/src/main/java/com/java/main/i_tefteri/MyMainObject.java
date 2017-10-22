package com.java.main.i_tefteri;


import android.app.Application;
import android.content.Context;

public class MyMainObject extends Application {
    private String _userid;
    private String _bank_id;
    private String _account_id;
    private String _IBAN;
    private String _Owner_Name;
    private double _Amount;
    private String _UserName;
//    private static MyMainObject instance;
    //@Override

//    private static MyMainObject instance;
//
//    public MyMainObject() {
//        instance = this;
//    }
//
//    public static Context getContext() {
//        return instance;
//    }
//    public void onCreate() {
//        super.onCreate();
////        instance = this;
//    }

    public void set_userid(String value){
        _userid=value;
    }

    public String get_userid(){
        return _userid;
    }

    public void set_bank_id(String value){
        _bank_id=value;
    }

    public String get_bank_id(){
        return _bank_id;
    }

    public void set_account_id(String value){
        _account_id=value;
    }

    public String get_account_id(){
        return _account_id;
    }

    public void set_IBAN(String value){
        _IBAN=value;
    }

    public String get_IBAN(){
        return _IBAN;
    }

    public void set_Owner_Name(String value){
        _Owner_Name=value;
    }

    public String get_Owner_Name(){
        return _Owner_Name;
    }

    public void set_Amount(double value){
        _Amount=value;
    }

    public double get_Amount(){
        return _Amount;
    }

    public void setUserName(String value){
        _UserName=value;
    }

    public String get_UserName(){
        return _UserName;
    }
}

