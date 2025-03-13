package com.example.bookinghotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class personalInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        Button btnSave = findViewById(R.id.button);

        EditText edAddress = findViewById(R.id.edAddress);
        EditText edName = findViewById(R.id.edName);
        EditText edPhone = findViewById(R.id.edphone);
        EditText edEmail = findViewById(R.id.edEmail);
        EditText edNum = findViewById(R.id.edNum);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edName.getText().toString().equals("")) {
                    Toast.makeText(personalInfo.this, "Please enter Name", Toast.LENGTH_SHORT).show();
                } else if (edEmail.getText().toString().equals("")) {
                    Toast.makeText(personalInfo.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                } else if (edPhone.getText().toString().equals("")) {
                    Toast.makeText(personalInfo.this, "Please enter Phone", Toast.LENGTH_SHORT).show();
                } else if (edAddress.getText().toString().equals("")) {
                    Toast.makeText(personalInfo.this, "Please enter Address", Toast.LENGTH_SHORT).show();
                } else if (edNum.getText().toString().equals("")) {
                    Toast.makeText(personalInfo.this, "Please enter Number of Persons", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(personalInfo.this, RoomInfo.class);
                    intent.putExtra("name", edName.getText().toString());
                    intent.putExtra("address", edAddress.getText().toString());
                    intent.putExtra("phone", edPhone.getText().toString());
                    intent.putExtra("numberofperson", edNum.getText().toString());
                    intent.putExtra("email", edEmail.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }
}