package com.example.companydetails;

import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private static final String TAG = "FirebaseHelper";
    private DatabaseReference databaseReference;

    public FirebaseHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference("companies");
    }

    // Save a company object to Firebase
    public void saveCompanyToFirebase(Company company) {
        databaseReference.push().setValue(company)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Company saved successfully."))
                .addOnFailureListener(e -> Log.e(TAG, "Failed to save company: " + e.getMessage()));
    }
}
