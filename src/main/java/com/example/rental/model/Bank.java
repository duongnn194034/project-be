package com.example.rental.model;

import java.io.Serializable;

public class Bank implements Serializable {
    private String bankName;
    private String holderName;
    private String accountNumber;
    private String[] sortCode = new String[3];

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String[] getSortCode() {
        return sortCode;
    }

    public void setCode(int index, String code) {
        if (index < 0 || index >= this.sortCode.length) {
            return;
        }
        this.sortCode[index] = code;
    }

    public void setSortCode(String[] sortCode) {
        this.sortCode = sortCode;
    }
}
