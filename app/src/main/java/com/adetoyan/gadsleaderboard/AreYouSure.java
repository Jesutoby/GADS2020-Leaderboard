package com.adetoyan.gadsleaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AreYouSure extends AppCompatActivity {
    private static final String BASE_URL = "https://docs.google.com/forms/d/e/";
    String emailAddress, firstName, lastName, projectLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.are_you_sure);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .6));

        //Get String extras from intent

        Intent intent = getIntent();
        firstName = intent.getStringExtra("First Name");
        lastName = intent.getStringExtra("Last Name");
        emailAddress = intent.getStringExtra("Email");
        projectLink = intent.getStringExtra("gitLink");

        // Create logger
        HttpLoggingInterceptor logger =
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        // Create OkHttp Client
        OkHttpClient.Builder okHttp =
                new OkHttpClient.Builder().addInterceptor(logger);

        //Initialize Retrofit
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp.build());
        final Retrofit retrofit = builder.build();

        //Start The SubmitWebservice
        final PostService service = retrofit.create(PostService.class);
        Button submit = findViewById(R.id.submit_project);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Void> call = service.postData(firstName, lastName, emailAddress, projectLink);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(AreYouSure.this, SubmissionSuccessful.class));
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(AreYouSure.this, SubmissionFailed.class));
                    }
                });
            }
        });

        ImageView cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

