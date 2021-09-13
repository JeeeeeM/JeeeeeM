            package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText username,password,firstName,lastName,mobile,repassword;
    Button Signup;
    TextView Signin2;
    DBHelper  DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.lastName);
        mobile = (EditText)findViewById(R.id.Mobile);
        repassword = (EditText)findViewById(R.id.repassword);
        Signup= (Button) findViewById(R.id.Sign_up);
        DB = new DBHelper(this);
        Signin2 = findViewById(R.id.Sign_in1);
        String text= "Already have an account? Sign in";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1= new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(Signup.this,"Loading..",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        };
        ss.setSpan(clickableSpan1, 25,32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Signin2.setText(ss);
        Signin2.setMovementMethod(LinkMovementMethod.getInstance());


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass = password.getText().toString();
                String user = username.getText().toString();
                String first= firstName.getText().toString();
                String last = lastName.getText().toString();
                String mob = mobile.getText().toString();
                String rePass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||first.equals   ("")||last.equals("")||mob.equals("")||rePass.equals(""))
                    Toast.makeText(Signup.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(rePass))
                    {
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false)
                        {
                            Boolean insert = DB.insertData(user, pass, first,last,mob);
                            if(insert ==true)
                            {
                                Toast.makeText(Signup.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeAct.class);

                                startActivity(intent);
                            }else{
                                Toast.makeText(Signup.this,"User already exist! please sign in",Toast.LENGTH_SHORT).show();
                            }


                        }
                    }else{
                        Toast.makeText(Signup.this,"Password not match!",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


    }
}