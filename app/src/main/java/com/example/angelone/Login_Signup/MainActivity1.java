package com.example.angelone.Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.angelone.R;


public class MainActivity1 extends AppCompatActivity {
    Button next;
    EditText mMobileEditText;
    ImageButton backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        next = findViewById(R.id.enterButton);


        mMobileEditText = findViewById(R.id.mobileEditText);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = mMobileEditText.getText().toString().trim();


                if (TextUtils.isEmpty(mobile)) {
                    mMobileEditText.setError("Mobile number is required.");
                    mMobileEditText.requestFocus();
                    return;
                }

                if (mobile.length() != 10) {
                    mMobileEditText.setError("Invalid mobile number.");
                    mMobileEditText.requestFocus();
                    return;
                }

                Intent intent = new Intent(MainActivity1.this, MainPage.class);
                startActivity(intent);
                finish();
            }
        });
        backbtn = findViewById(R.id.backButton1);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}