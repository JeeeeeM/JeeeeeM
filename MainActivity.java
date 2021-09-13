package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.DngCreator;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button Signin;

    DBHelper DB;

    String user2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        Signin = (Button) findViewById(R.id.Sign_in);
        TextView Sign_up=(TextView) findViewById(R.id.Sign_up1);
        DB = new DBHelper(this);
       String text= "Don't have an account yet? Sign up";
        SpannableString ss = new SpannableString(text);
       ClickableSpan clickableSpan1= new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this,"Loading..",Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(getApplicationContext(),Signup.class);
               startActivity(intent);
            }
        };
        ss.setSpan(clickableSpan1, 27,34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Sign_up.setText(ss);
        Sign_up.setMovementMethod(LinkMovementMethod.getInstance());

        Signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user2 =username.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkusernamepassword = DB.checkusernamepassword(user, pass);

                    if(checkusernamepassword == true) {
                  //  Boolean get_first = DB.get_first(user);

                        Toast.makeText(MainActivity.this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeAct.class);
                            intent.putExtra("user2",user);
                        startActivity(intent);
                    }else{
                       
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




    }
}