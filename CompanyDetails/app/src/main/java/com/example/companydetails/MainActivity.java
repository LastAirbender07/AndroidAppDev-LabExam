package com.example.companydetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
        EditText companyName, description, foundedDate;
        Button saveButton, searchButton;
        DatabaseHelper myDb;
        FirebaseHelper firebaseHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            FirebaseApp.initializeApp(this);
            setContentView(R.layout.activity_main);

            companyName = findViewById(R.id.companyName);
            description = findViewById(R.id.description);
            foundedDate = findViewById(R.id.foundedDate);
            saveButton = findViewById(R.id.saveButton);
            searchButton = findViewById(R.id.searchButton);

            myDb = new DatabaseHelper(this);
            firebaseHelper = new FirebaseHelper();

            saveButton.setOnClickListener(v -> {
                String name = companyName.getText().toString();
                String desc = description.getText().toString();
                String date = foundedDate.getText().toString();

                if (!name.isEmpty() && !desc.isEmpty() && !date.isEmpty()) {
                    myDb.insertData(name, desc, date);
                    firebaseHelper.saveCompanyToFirebase(new Company(name, desc, date));
                    Toast.makeText(MainActivity.this, "Company Saved", Toast.LENGTH_SHORT).show();
                    // Navigate to CompanyListActivity
                    Intent intent = new Intent(MainActivity.this, CompanyListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            });

           searchButton.setOnClickListener(v -> {
               Intent intent = new Intent(MainActivity.this, CompanyListActivity.class);
                startActivity(intent);
            });
        }

    }
