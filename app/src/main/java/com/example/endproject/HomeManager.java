package com.example.endproject;

public class HomeManager extends Person{
    private int seniority;


    public HomeManager(){}
    public HomeManager(String id,String firstName,String password,String email,String lastName ,int seniority) {
        super(id,firstName,lastName,password,email);
        this.seniority = seniority;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

}
