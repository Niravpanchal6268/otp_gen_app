package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MaterialButton button;
    private static final String CHANNEL_id = "otp channel";
    private static final int NOTIFICATION_id = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.login_btn_id_);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String op = String.valueOf(otpgen(6));
                Intent i = new Intent(MainActivity.this, otp_gen.class);
                i.putExtra("o", op);
                startActivity(i);
                Toast.makeText(MainActivity.this, "otp send successfull", Toast.LENGTH_SHORT).show();

                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.otp_large_icon, null);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap largeIcon = bitmapDrawable.getBitmap();

                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                Notification otpnotification;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    otpnotification = new Notification.Builder(MainActivity.this)
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.ic_baseline_sms_24)
                            .setContentText("Your OTP:"+op +" For Login Verification Thanks")
                            .setSubText("new message from app")
                            .setChannelId(CHANNEL_id)
                            .build();
                    nm.createNotificationChannel(new NotificationChannel(CHANNEL_id, "new channel", NotificationManager.IMPORTANCE_HIGH));

                } else {
                    otpnotification = new Notification.Builder(MainActivity.this)
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.ic_baseline_sms_24)
                            .setContentText("Otp Message")
                            .setSubText("new message from app")
                            .build();
                }

                nm.notify(NOTIFICATION_id,otpnotification);
            }
        });


    }

    private static char[] otpgen(int length) {
        String number = "1234567890";
        Random random = new Random();
        char[] otp = new char[length];

        for (int i = 0; i < length; i++) {
            otp[i] = number.charAt(random.nextInt(number.length()));
        }
        return otp;

    }

}