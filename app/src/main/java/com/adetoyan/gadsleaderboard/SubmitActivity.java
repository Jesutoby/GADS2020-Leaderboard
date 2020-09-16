package com.adetoyan.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class SubmitActivity extends AppCompatActivity {
    //    private LearnAdapter adapter;
//    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    private List<String> mWordList;
    EditText emailA;
    EditText firstN;
    EditText lastN;
    EditText projectL;
    private Context context;
    private static final String BASE_URL = "https://docs.google.com/forms/d/e/";

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        /*if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
        }*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

//        toolbar.setNavigationIcon(R.drawable.submit);
        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });*/

        Button submit;
        emailA = findViewById(R.id.email_address);
        firstN = findViewById(R.id.first_name);
        lastN = findViewById(R.id.last_name);
        projectL = findViewById(R.id.github_link);
        submit = findViewById(R.id.project_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCustomDialog();
            }
        });


    }

    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.are_you_sure, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ImageView cancelling = dialogView.findViewById(R.id.cancel);
        Button yes = dialogView.findViewById(R.id.submit_project);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        cancelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
                vetData();
            }
        });
    }

    private void vetData() {
        String firstName = firstN.getText().toString();
        String lastName = lastN.getText().toString();
        String emailAddress = emailA.getText().toString();
        String projectLink = projectL.getText().toString();
        if (firstName.isEmpty() || lastName.isEmpty() || emailAddress.isEmpty() || projectLink.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        }
        else {
            projectSubmission(firstName, lastName, emailAddress, projectLink);
        }
    }

    private void projectSubmission(String firstName, String lastName, String emailAddress, String projectLink) {
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
        service.postData(firstName, lastName, emailAddress, projectLink).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    submissionSuccessful();
                }
                else
                    submissionFailed();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                submissionFailed();
            }
        });

    }

    private void submissionFailed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        View layoutView = getLayoutInflater().inflate(R.layout.activity_submission_failed, null);
        builder.setView(layoutView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void submissionSuccessful() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        View layoutView = getLayoutInflater().inflate(R.layout.activity_submission_successful, null);
        builder.setView(layoutView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        return true;
    }
}