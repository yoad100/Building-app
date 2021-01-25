package com.example.endproject;

public class Bill {
    private int month;
    private int amount;
    private int departmentNum;

    public Bill(){}
    public Bill(int month, int amount, int departmentNum) {
        this.month = month;
        this.amount = amount;
        this.departmentNum = departmentNum;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(int departmentNum) {
        this.departmentNum = departmentNum;
    }
}
