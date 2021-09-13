package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
String userid;
Button payment;
DBHelper DB;
Button ChangePass;
Button enterpass;
EditText Newpass;
EditText ConNewPass;
Button Logoout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        payment = findViewById(R.id.payment);
        DB = new DBHelper(this);
        ChangePass = findViewById(R.id.ChangePass);
        enterpass = findViewById(R.id.enterpass);
        Newpass = findViewById(R.id.CurPass);
        ConNewPass = findViewById(R.id.ConCurPass);
        Logoout = findViewById(R.id.Logout);
        ChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Newpass.setVisibility(View.VISIBLE);
        enterpass.setVisibility(View.VISIBLE);
        ConNewPass.setVisibility(View.VISIBLE);

            }
        });
        enterpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid = getIntent().getExtras().getString("ID");
                String curpass=Newpass.getText().toString();
                String conpass=ConNewPass.getText().toString();

                Boolean updatepassword= DB.updatepass(userid,curpass,conpass);
                if(updatepassword== true)
                Toast.makeText(Profile.this,"Entry updated",Toast.LENGTH_SHORT).show();
            }
        });

        Logoout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid = getIntent().getExtras().getString("ID");

                if (userid.equals("admin")) {


                    Cursor res = DB.getalldata();
                    if (res.getCount() == 0) {
                        Toast.makeText(Profile.this, "No Booking", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("ID: " + res.getString(0) + "\n");
                        buffer.append("User: " + res.getString(1) + "\n");
                        buffer.append("Check-IN: " + res.getString(2) + "\n");
                        buffer.append("Check-Out: " + res.getString(3) + "\n");
                        buffer.append("Room Size: " + res.getString(4) + "\n");
                        buffer.append("Room Qty: " + res.getString(5) + "\n");
                        buffer.append("Hours: " + res.getString(6) + "\n\n");

                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
                    builder.setCancelable(true);
                    builder.setTitle("Bookings!");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }else{
                    Cursor res = DB.getdata(userid);
                    if (res.getCount() == 0) {
                        Toast.makeText(Profile.this, "No Booking", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("ID: " + res.getString(0) + "\n");
                        buffer.append("User: " + res.getString(1) + "\n");
                        buffer.append("Check-IN: " + res.getString(2) + "\n");
                        buffer.append("Check-Out: " + res.getString(3) + "\n");
                        buffer.append("Room Size: " + res.getString(4) + "\n");
                        buffer.append("Room Qty: " + res.getString(5) + "\n");
                        buffer.append("Hours: " + res.getString(6) + "\n\n");

                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
                    builder.setCancelable(true);
                    builder.setTitle("Bookings!");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });

    }
}