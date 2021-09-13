package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class BookNow extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView back2;
    Spinner room;
    Spinner guest;
    Spinner Noroom;
    Button Book;
    EditText checkn,checko;
    DBHelper DB;
    String userid;
    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        userid = getIntent().getExtras().getString("ID");
        back2 = findViewById(R.id.back1);
        checkn =(EditText) findViewById(R.id.CheckIn);
        checko =(EditText) findViewById(R.id.CheckOut);
        Spinner mySpinner3 = (Spinner) findViewById(R.id.spinner3);
        Book = (Button) findViewById(R.id.Booker);
        DB = new DBHelper(this);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        checkn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BookNow.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        checkn.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        checko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BookNow.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        checko.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



//        Book.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String check1 = checkin.getText().toString();
//                String check2 = checkout.getText().toString();
//             //   String spin1= room.getText().toString();
//              //  String last = lastName.getText().toString();
//              //  String mob = mobile.getText().toString();
//             //   String rePass = repassword.getText().toString();
//            }
//        });
//
        String text= "<-Back";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1= new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(BookNow.this,"Loading..",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),HomeAct.class);
                startActivity(intent);
            }
        };
        ss.setSpan(clickableSpan1, 0,6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        back2.setText(ss);
        back2.setMovementMethod(LinkMovementMethod.getInstance());

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(BookNow.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Rooms));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        Spinner mySpinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(BookNow.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.TotalRoom));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);



        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(BookNow.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Time));
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner3.setAdapter(myAdapter3);



        mySpinner.setOnItemSelectedListener(this);
        mySpinner2.setOnItemSelectedListener(this);
        mySpinner3.setOnItemSelectedListener(this);
        Book.setOnClickListener(new View.OnClickListener() {
      //      if(checkn.getValue().isAfter(checko.getValue()))
            public String generateString(int length)
            {
            char[] chars="ABCDEFGHIIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
            StringBuilder stringbuilder = new StringBuilder();
            Random random= new Random();
            for(int i= 0;i<length;i++)
            {
                char c= chars[random.nextInt(chars.length)];
                stringbuilder.append(c);
            }
            return stringbuilder.toString();
            }

            @Override
            public void onClick(View view) {
                String ID =generateString(6);
                String check1 = checkn.getText().toString();
                String check2 = checko.getText().toString();

                String text1 = mySpinner.getSelectedItem().toString();
                String text2 = mySpinner2.getSelectedItem().toString();
                String text3 = mySpinner3.getSelectedItem().toString();
                String ownid = getIntent().getExtras().getString("ID");

                if (check1.equals("") || check2.equals("") || text1.equals("") || text2.equals("") || text3.equals(""))
                    Toast.makeText(BookNow.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    System.out.println(check1);
                    System.out.println(check2);
                    System.out.println(text1);
                    System.out.println(text2);
                    System.out.println(text3);
                    System.out.println(ID);



                        Boolean checkuser = DB.checkBooking(ID);
                        if (checkuser == false) {
                            Boolean insert = DB.Booking(ID,ownid, check1, check2, text1, text2, text3);
//                        if (insert == true) {
                            Toast.makeText(BookNow.this, "Booking Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeAct.class);
                            intent.putExtra("idgen", ownid);
                            startActivity(intent);
                        } else {
                            Toast.makeText(BookNow.this, "Date not Available", Toast.LENGTH_SHORT).show();
                        }

                    }





            }
        });


}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}