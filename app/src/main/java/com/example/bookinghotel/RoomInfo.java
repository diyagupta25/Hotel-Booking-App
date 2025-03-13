package com.example.bookinghotel;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RoomInfo extends AppCompatActivity {

    Calendar myCalendar;
    EditText edCheckin, edCheckout, edNumRooms;
    Spinner spinType;
    Button btnPreview;
    String name, email, phone, address, numberofperson;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);

        // Receiving data from previous activity
        Intent i = getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        phone = i.getStringExtra("phone");
        address = i.getStringExtra("address");
        numberofperson = i.getStringExtra("numberofperson");

        myCalendar = Calendar.getInstance();

        edCheckin = findViewById(R.id.edCheckin);
        edCheckout = findViewById(R.id.edCheckout);
        edNumRooms = findViewById(R.id.edName); // Assuming you have this field
        spinType = findViewById(R.id.spinType);
        btnPreview = findViewById(R.id.btnPreview);

        // Date Picker for Check-in Date
        edCheckin.setOnClickListener(v -> new DatePickerDialog(RoomInfo.this, (view, year, month, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(edCheckin);
        }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        // Date Picker for Check-out Date
        edCheckout.setOnClickListener(v -> new DatePickerDialog(RoomInfo.this, (view, year, month, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(edCheckout);
        }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        // Set OnClickListener for Preview Button
        btnPreview.setOnClickListener(v -> {
            String checkinDate = edCheckin.getText().toString();
            String checkoutDate = edCheckout.getText().toString();
            String numRooms = edNumRooms.getText().toString();
            String roomType = spinType.getSelectedItem().toString();

            Intent intent = new Intent(RoomInfo.this, FinalActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("phone", phone);
            intent.putExtra("address", address);
            intent.putExtra("numberofperson", numberofperson);
            intent.putExtra("roomtype", roomType);
            intent.putExtra("checkin", checkinDate);
            intent.putExtra("checkout", checkoutDate);
            intent.putExtra("num", numRooms);

            startActivity(intent); // Start FinalActivity
        });
    }

    private void updateLabel(EditText editText) {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(sdf.format(myCalendar.getTime()));
    }
}
