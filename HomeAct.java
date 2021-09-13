        package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

    public class HomeAct extends AppCompatActivity {
    TextView wel;
    String user3;
    DBHelper DB;
    Button Book1;
    Button Room1;
    Button Mybook;
    Button MyProfile;


    String ID ;
    TextView ShowId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        user3= getIntent().getExtras().getString("user2");
        Book1 = findViewById(R.id.Book);
        Room1 = findViewById(R.id.Room);
        Mybook = findViewById(R.id.MyBookings);
        ShowId= findViewById(R.id.ShowId);
        wel = findViewById(R.id.wel);
        MyProfile= findViewById(R.id.MyProfile);
        DB = new DBHelper(this);


        ID = getIntent().getExtras().getString("IDgen");
       // Show();
        ShowId.setText(ID);
        Welcome();

        Book1.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
           Intent intent = new Intent(getApplicationContext(),BookNow.class);
                intent.putExtra("ID",user3);
           startActivity(intent);

           }
       });
        Room1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Prices.class);
                startActivity(intent);
            }
        });
        MyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Profile.class);
                intent.putExtra("ID",user3);
                startActivity(intent);

            }
        });

        Mybook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idgen = getIntent().getExtras().getString("idgen");
                if (user3.equals("admin")) {


                    Cursor res = DB.getalldata();
                    if (res.getCount() == 0) {
                        Toast.makeText(HomeAct.this, "No Booking", Toast.LENGTH_SHORT).show();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomeAct.this);
                    builder.setCancelable(true);
                    builder.setTitle("Bookings!");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }else{
                    Cursor res = DB.getdata(user3);
                    if (res.getCount() == 0) {
                        Toast.makeText(HomeAct.this, "No Booking", Toast.LENGTH_SHORT).show();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomeAct.this);
                    builder.setCancelable(true);
                    builder.setTitle("Bookings!");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });
    }


    private void Welcome() {

        Cursor res = DB.getuserdata(user3);
//        StringBuffer buffer = new StringBuffer();
//       // buffer.append(getdata);
//        // buffer.append(getdata);
//        while(res.moveToNext())
//        {
//            buffer.append("Welcome, "+res.getString(2)+"\n");
//
//        }
        wel.setText("Welcome,\n"+user3+"!");


     //   System.out.println(getdata);
    }

    private void Show() {
        Cursor res = DB.getuserdata(ID);
        StringBuffer buffer = new StringBuffer();
        // buffer.append(getdata);
        // buffer.append(getdata);
        while(res.moveToNext())
        {
            buffer.append("Welcome"+res.getString(2));

        }
        wel.setText(buffer.toString());

        }

    }

