    package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";
    private static final String Table1 = "users";
    private static final String Table2 = "booking";
    private static final String Table3 = "table3";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        String table1 = "CREATE TABLE " + Table1 + "(username TEXT primary key,password TEXT,first_name TEXT,last_name TEXT,mobile TEXT)";
        String table2 = "CREATE TABLE " + Table2 + "(ID TEXT primary key,user TEXT,CheckIn TEXT,CheckOut TEXT,Spinner TEXT,Spinner2 TEXT,Spinner3 TEXT,Price TEXT)";
        MyDB.execSQL(table1);
        //ID INTEGER primary key autoincrement,
        MyDB.execSQL(table2);
        //    MyDB.execSQL("create Table booking( Spinner TEXT,CheckIn TEXT,CheckOut TEXT,Spinner2 TEXT,Spinner3 Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists " + Table1);
        MyDB.execSQL("drop Table if exists " + Table2);
        //MyDB.execSQL("drop Table if exists booking");
        onCreate(MyDB);
    }

    public Boolean updateuserdata(String name,String newpass,String confirmpass)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Newpass",newpass);
        contentValues.put("ConNewPass",confirmpass);
        Cursor cursor = DB.rawQuery("Select * from users where name =?", new String[]{name});
        if(cursor.getCount()>0) {


            long result = DB.update("users", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;

            } else {
                return true;
            }
        }else
        {
            return false;
        }
    }

    public Boolean insertData(String username, String password, String first_name, String last_name, String mobile) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("mobile", mobile);


        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else
            return true;

    }

    public Boolean Booking(String ID,String user,String CheckIn, String CheckOut, String Spinner, String Spinner2, String Spinner3) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", ID);
        contentValues.put("user", user);
        contentValues.put("CheckIn", CheckIn);
        contentValues.put("CheckOut", CheckOut);
        contentValues.put("Spinner", Spinner);
        contentValues.put("Spinner2", Spinner2);
        contentValues.put("Spinner3", Spinner3);
        contentValues.put("Price", "175");





       // if (Spinner == "Regular") {
            Cursor cursor = MyDB.rawQuery("Select * from booking where  Spinner=?", new String[]{Spinner});
            long result = MyDB.insert("booking", null, contentValues);

            if (result == -1) return false;
            else
                return true;
      //  }
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;


    }

    public Boolean checkBooking(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from booking where ID = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;


    }

    public List<HomeAct> GetBookingList() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String sql = "select * from " + Table2;
        MyDB = this.getReadableDatabase();
        List<HomeAct> storedata = new ArrayList<>();
        Cursor cursor = MyDB.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                String check1 = cursor.getString(0);
                String check2 = cursor.getString(1);
                String room = cursor.getString(2);
                String roomqty = cursor.getString(3);
                String pax = cursor.getString(4);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return storedata;

    }


    public Boolean checkusernamepassword(String username, String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?",new String[] {username,password});
         if (cursor.getCount()>0)
             return true;
         else
             return false;
    }
    public Cursor getdata(String ID)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from booking where user =?",new String[] {ID});
       // Cursor cursor = MyDB.rawQuery("Select * from booking ",null);
        return cursor;

    }
    public Boolean updatepass(String userdata,String curpass,String conpass) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", curpass);
      //  contentValues.put("conpass", conpass);
        Cursor cursor = DB.rawQuery("Select * from users where  username=?", new String[]{userdata});
        long result = 0;
        if (cursor.getCount() > 0) {
            result = DB.update("users", contentValues, "username=?", new String[]{userdata});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getalldata()
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from booking",null);
        // Cursor cursor = MyDB.rawQuery("Select * from booking ",null);
        return cursor;

    }
//    public Boolean Update(String userN)
//    {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//        Cursor  cursor = MyDB.rawQuery("Select * from user where username =?",new String[]{userN});
//
//
//    }

    public Cursor getuserdata(String user3)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username =?",new String[]{user3});
        return cursor;

    }

}
