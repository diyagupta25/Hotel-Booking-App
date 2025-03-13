package com.example.bookinghotel;  // Ensure this matches your app's package name

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnBookSingle, btnBookDouble, btnBookDeluxe, btnBookSuperDeluxe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // ✅ Ensure correct layout reference

        // ✅ Initialize all buttons
        btnBookSingle = findViewById(R.id.btnBookSingle);
        btnBookDouble = findViewById(R.id.btnBookDouble);
        btnBookDeluxe = findViewById(R.id.btnBookDeluxe);
        btnBookSuperDeluxe = findViewById(R.id.btnBookSuperDeluxe);

        // ✅ Check if buttons are found to prevent crash
        if (btnBookSingle == null || btnBookDouble == null || btnBookDeluxe == null || btnBookSuperDeluxe == null) {
            Log.e("MainActivity", "One or more buttons not found in XML!");
            return;
        }

        // ✅ Set click listeners
        btnBookSingle.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, personalInfo.class);
            intent.putExtra("info", "Single Bed Room");
            startActivity(intent);
        });

        btnBookDouble.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, personalInfo.class);
            intent.putExtra("info", "Double Bed Room");
            startActivity(intent);
        });

        btnBookDeluxe.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, personalInfo.class);
            intent.putExtra("info", "Deluxe Room");
            startActivity(intent);
        });

        btnBookSuperDeluxe.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, personalInfo.class);
            intent.putExtra("info", "Super Deluxe Room");
            startActivity(intent);
        });
    }
}
