package com.adetoyan.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class SubmitActivity extends AppCompatActivity {
//    private LearnAdapter adapter;
//    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    private List<String> mWordList;
    EditText emailAddress;
    EditText firstName;
    EditText lastName;
    EditText projectLink;
    private Context context;

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
        emailAddress = findViewById(R.id.email_address);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        projectLink = findViewById(R.id.github_link);
        submit = findViewById(R.id.project_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Convert Edittext Values to string
                String fName, lName, eAddress, gitLink;
                fName = firstName.getText().toString();
                lName = lastName.getText().toString();
                eAddress = emailAddress.getText().toString();
                gitLink = projectLink.getText().toString();
                //Send String data into the PopUp activity
                Intent intent = new Intent(SubmitActivity.this, AreYouSure.class);
                intent.putExtra("First Name",fName);
                intent.putExtra("Last Name",lName);
                intent.putExtra("Email",eAddress);
                intent.putExtra("gitLink",gitLink);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
        return true;
    }
}
