package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class otp_gen extends AppCompatActivity {

    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_gen);

        editText = findViewById(R.id.edit_id);
        String totp= getIntent().getStringExtra("o");
        button =findViewById(R.id.sumbit_btn_id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String eotp=editText.getText().toString();

                if(totp.equals(eotp))
                {
                    Toast.makeText(otp_gen.this, "otp varified", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(otp_gen.this,successfull_verification_page.class));
                }
                else
                {
                    Toast.makeText(otp_gen.this, "otp wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}