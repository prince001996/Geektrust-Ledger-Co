package com.ledger;

public class Borrower {
    private static int uniqueBorrowerId = 1;
    private int borrowerId;
    private String borrowerName;
    private Loan loan;
    
    Borrower(String name){
        this.borrowerId = getUniqueBorrowerId();
        this.borrowerName = name;
    }
    
    public String getBorrowerName() {
        return borrowerName;
    }
    
    public Loan getLoan() {
        return loan;
    }
    
    public void setLoan(Loan loan) {
        this.loan = loan;
    }
    
    private int getUniqueBorrowerId() {
        return uniqueBorrowerId++;
    }
    
    public void applyForLoan(Bank bank, double principal, double interestRate, double noOfYears) {
        bank.sanctionLoan(this, principal, interestRate, noOfYears);
    }
}
