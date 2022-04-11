package com.ledger;

import java.util.HashMap;

public class Bank {
    private String bankName;
    private HashMap<String, Borrower> borrowerMap;
    
    Bank(String bankName){
        this.bankName = bankName;
        this.borrowerMap = new HashMap<>();
    }
    
    public String getBankName() {
        return bankName;
    }
    
    public HashMap<String, Borrower> getBorrowerMap() {
        return borrowerMap;
    }
    
    public void sanctionLoan(Borrower borrower, double prinicipal, double interestRate, double noOfYears){
        borrowerMap.putIfAbsent(borrower.getBorrowerName(), borrower);
        Loan newLoan = new Loan(prinicipal, interestRate, noOfYears);
        borrower.setLoan(newLoan);
    }
}
