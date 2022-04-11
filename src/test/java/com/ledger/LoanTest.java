package com.ledger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    @Test
    void getLastEmiNumber() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();;
        loan.updateRemainingEmis(550, 1);
        assertEquals(13, loan.getLastEmiNumber(1));
    }

    @Test
    void getAmountPaid() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();
        assertEquals(0, loan.getAmountPaid(0));
        assertEquals(50, loan.getAmountPaid(1));
        assertNotEquals(55, loan.getAmountPaid(2));
    }

    @Test
    void getAmount() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();
        assertEquals(1200, loan.getAmount());
    }

    @Test
    void getNoOfEmi() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();
        assertEquals(24, loan.getNoOfEmi());
    }

    @Test
    void updateRemainingEmis() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();
        loan.updateRemainingEmis(550, 1);
        assertEquals(500, loan.getEmiList().get(3).getRemainingAmountAfterCurrentEmi());
    }
}