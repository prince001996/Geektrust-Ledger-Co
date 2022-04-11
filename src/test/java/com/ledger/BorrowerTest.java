package com.ledger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorrowerTest {
    
    @Test
    void getBorrowerName() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Ravi");
        assertEquals("Ravi", borrower.getBorrowerName());
    }
    
    @Test
    void getLoan() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Bittu");
        borrower.applyForLoan(bank, 1000, 10, 2);
        assertEquals(1200, borrower.getLoan().getAmount());
    }
    
    @Test
    void setLoan() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Bittu");
        Loan loan = new Loan(1000, 10, 2);
        borrower.setLoan(loan);
        assertEquals(1200, borrower.getLoan().getAmount());
    }
    
    @Test
    void applyForLoan() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Bittu");
        borrower.applyForLoan(bank, 1000, 10, 2);
        assertEquals(1200, borrower.getLoan().getAmount());
    }
}