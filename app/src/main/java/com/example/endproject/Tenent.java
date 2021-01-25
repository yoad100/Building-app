package com.example.endproject;

public class Tenent extends Person{
    private int departmentNumber;
    private int monthlyPayment;

    public Tenent(){}
    public Tenent(String id,String firstName,String lastName,String password ,String email,int departmentNumber, int monthlyPayment) {
        super(id,firstName,lastName,password,email);
        this.departmentNumber = departmentNumber;
        this.monthlyPayment = monthlyPayment;
    }
    public int getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(int departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public int getmonthlyPayment() {
        return monthlyPayment;
    }

    public void setmonthlyPayment(int monthlyPayment) {
        monthlyPayment = monthlyPayment;
    }


}
