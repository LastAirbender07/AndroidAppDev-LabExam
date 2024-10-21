package com.example.companydetails;

public class Company {
    private String companyName;
    private String description;
    private String foundedDate;

    public Company() {}

    public Company(String companyName, String description, String foundedDate) {
        this.companyName = companyName;
        this.description = description;
        this.foundedDate = foundedDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDescription() {
        return description;
    }

    public String getFoundedDate() {
        return foundedDate;
    }
}

