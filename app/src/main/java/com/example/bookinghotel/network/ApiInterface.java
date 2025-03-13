package com.example.bookinghotel.network;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @Multipart
    @POST("hotelUsers.php")
    Call<String> postData(
            @Part("name") RequestBody name, // RequestBody used, but incorrect in FinalActivity.java
            @Part("email") RequestBody email,
            @Part("phone") RequestBody phone,
            @Part("address") RequestBody address,
            @Part("numberofperson") RequestBody numberOfPerson,
            @Part("roomType") RequestBody roomType,
            @Part("checkin") RequestBody checkin,
            @Part("checkout") RequestBody checkout,
            @Part("num") RequestBody num
    );

}
