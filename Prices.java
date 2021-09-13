package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Prices extends AppCompatActivity {
TextView back2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices);

        back2 = findViewById(R.id.back1);
        String text= "<-Back";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1= new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(Prices.this,"Loading..",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),HomeAct.class);
                startActivity(intent);
            }
        };
        ss.setSpan(clickableSpan1, 0,6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        back2.setText(ss);
        back2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}