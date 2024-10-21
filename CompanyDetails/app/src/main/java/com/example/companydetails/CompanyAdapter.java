//package com.example.companydetails;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//
//public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {
//    private final ArrayList<Company> companies;
//
//    public CompanyAdapter(ArrayList<Company> companies) {
//        this.companies = companies;
//    }
//
//    @NonNull
//    @Override
//    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
//        return new CompanyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
//        Company company = companies.get(position);
//        holder.text1.setText(company.getCompanyName()); // Use getCompanyName() instead of getName()
//        holder.text2.setText(company.getDescription());
////        holder.foundedDateTextView.setText(company.getFoundedDate());
//    }
//
//    @Override
//    public int getItemCount() {
//        return companies.size();
//    }
//
//    static class CompanyViewHolder extends RecyclerView.ViewHolder {
//        TextView text1;
//        TextView text2;
////        TextView foundedDateTextView;
//
//        public CompanyViewHolder(View itemView) {
//            super(itemView);
//            text1 = itemView.findViewById(android.R.id.text1);
//            text2 = itemView.findViewById(android.R.id.text2);
////            foundedDateTextView = itemView.findViewById(R.id.foundedDateTextView);
//        }
//    }
//}


package com.example.companydetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {
    private final ArrayList<Company> companies;

    public CompanyAdapter(ArrayList<Company> companies) {
        this.companies = companies;
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_item, parent, false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        Company company = companies.get(position);
        holder.text1.setText(company.getCompanyName());
        holder.text2.setText(company.getDescription());
        holder.foundedDateTextView.setText(company.getFoundedDate());
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    static class CompanyViewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        TextView text2;
        TextView foundedDateTextView;

        public CompanyViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
            foundedDateTextView = itemView.findViewById(R.id.foundedDateTextView);
        }
    }
}
