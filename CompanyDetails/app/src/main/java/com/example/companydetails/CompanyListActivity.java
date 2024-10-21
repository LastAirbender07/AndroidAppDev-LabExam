//package com.example.companydetails;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.SearchView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import java.util.ArrayList;
//
//public class CompanyListActivity extends AppCompatActivity {
//
//    DatabaseHelper myDb;
//    ListView companyListView;
//    SearchView searchView;
//    ArrayAdapter<String> adapter;
//    ArrayList<String> companyList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_company_list);
//
//        // Ensure views are properly initialized
//        try {
//            companyListView = findViewById(R.id.companyListView);
//            searchView = findViewById(R.id.searchView);
//            myDb = new DatabaseHelper(this);
//            companyList = new ArrayList<>();
//
//            Cursor res = myDb.getAllData();
//            if (res == null || res.getCount() == 0) {
//                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            // Populate the company list
//            while (res.moveToNext()) {
//                companyList.add("Name: " + res.getString(1) + "\nDescription: " + res.getString(2) + "\nFounded: " + res.getString(3));
//            }
//
//            // Set up adapter
//            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, companyList);
//            companyListView.setAdapter(adapter);
//
//            // Set up search view filtering
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    adapter.getFilter().filter(newText);
//                    return false;
//                }
//            });
//        } catch (Exception e) {
//            Log.e("CompanyListActivity", "Error in onCreate: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.company_list_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.add_company) {
//            // Navigate to MainActivity to add a new company
//            Intent intent = new Intent(CompanyListActivity.this, MainActivity.class);
//            startActivity(intent);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}


package com.example.companydetails;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CompanyListActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    RecyclerView companyRecyclerView;
    SearchView searchView;
    CompanyAdapter adapter;
    ArrayList<Company> companyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);

        // Initialize views and variables
        companyRecyclerView = findViewById(R.id.companyRecyclerView);
        searchView = findViewById(R.id.searchView);
        myDb = new DatabaseHelper(this);
        companyList = new ArrayList<>();

        // Fetch data from database
        loadCompanies();

        // Set up RecyclerView
        adapter = new CompanyAdapter(companyList);
        companyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        companyRecyclerView.setAdapter(adapter);

        // Set up search view filtering
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCompanies(newText);
                return false;
            }
        });
    }

    private void loadCompanies() {
        Cursor res = myDb.getAllData();
        if (res == null || res.getCount() == 0) {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
            return;
        }

        // Populate the company list
        while (res.moveToNext()) {
            companyList.add(new Company(res.getString(1), res.getString(2), res.getString(3)));
        }
    }

    private void filterCompanies(String query) {
        ArrayList<Company> filteredList = new ArrayList<>();
        for (Company company : companyList) {
            if (company.getCompanyName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(company);
            }
        }
        adapter = new CompanyAdapter(filteredList);
        companyRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.company_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_company) {
            Intent intent = new Intent(CompanyListActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
