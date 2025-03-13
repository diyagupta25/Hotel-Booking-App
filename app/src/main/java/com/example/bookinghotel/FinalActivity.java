package com.example.bookinghotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookinghotel.network.ApiInterface;
import com.example.bookinghotel.network.RetrofitClientInstance;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalActivity extends AppCompatActivity {

    String name, email, phone, address, numberofperson;
    String roomtype, checkinDate, checkoutDate, numberOfRooms;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        TextView textView = findViewById(R.id.tv1);
        btnSubmit = findViewById(R.id.btnSubmit);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        phone = i.getStringExtra("phone");
        address = i.getStringExtra("address");
        numberofperson = i.getStringExtra("numberofperson");
        roomtype = i.getStringExtra("roomtype");
        checkinDate = i.getStringExtra("checkin");
        checkoutDate = i.getStringExtra("checkout");
        numberOfRooms = i.getStringExtra("num");

        // Display Data
        String details = "Name: " + name +
                "\nEmail: " + email +
                "\nPhone: " + phone +
                "\nAddress: " + address +
                "\nNumber of Persons: " + numberofperson +
                "\nRoom Type: " + roomtype +
                "\nCheck-in Date: " + checkinDate +
                "\nCheck-out Date: " + checkoutDate +
                "\nNumber of Rooms: " + numberOfRooms;

        textView.setText(details);
        btnSubmit.setVisibility(View.VISIBLE);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData();
            }
        });



    }

    public void postData() {
        ApiInterface apiClient = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<String> call = apiClient.postData(
                getPart(name), getPart(email), getPart(phone),
                getPart(address), getPart(numberofperson), getPart(roomtype),
                getPart(checkinDate), getPart(checkoutDate), getPart(numberOfRooms)
        );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(FinalActivity.this, "Success: " + response.body(), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String errorResponse = response.errorBody().string();
                        Toast.makeText(FinalActivity.this, "Error: " + errorResponse, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(FinalActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(FinalActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private RequestBody getPart(String name) {
        if (name == null) {
            return RequestBody.create(MediaType.parse("text/plain"), "");
        } else {
            return RequestBody.create(MediaType.parse("text/plain"), name);
        }
    }

}
