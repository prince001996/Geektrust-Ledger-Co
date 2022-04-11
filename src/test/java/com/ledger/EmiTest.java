package com.ledger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmiTest {

    @Test
    void getEmiAmount() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();
        assertEquals(50, loan.getEmiList().get(0).getEmiAmount());
    }

    @Test
    void getAmountPaidTillCurrentEmi() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();
        loan.updateRemainingEmis(550, 1);
        assertEquals(700, loan.getEmiList().get(3).getAmountPaidTillCurrentEmi());
    }

    @Test
    void getRemainingAmountAfterCurrentEmi() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();
        loan.updateRemainingEmis(550, 1);
        assertEquals(500, loan.getEmiList().get(3).getRemainingAmountAfterCurrentEmi());
    }

    @Test
    void setEmiAmount() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();
        Emi emi = loan.getEmiList().get(0);
        emi.setEmiAmount(55);
        assertEquals(55, emi.getEmiAmount());
    }

    @Test
    void setAmountPaidTillCurrentEmi() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();
        Emi emi = loan.getEmiList().get(0);
        emi.setAmountPaidTillCurrentEmi(10);
        assertEquals(10, emi.getAmountPaidTillCurrentEmi());
    }

    @Test
    void setRemainingAmountAfterCurrentEmi() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();
        Emi emi = loan.getEmiList().get(0);
        emi.setRemainingAmountAfterCurrentEmi(10);
        assertEquals(10, emi.getRemainingAmountAfterCurrentEmi());
    }

    @Test
    void updateEmiWithLumpsum() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();
        Emi emi = loan.getEmiList().get(4);
        int amountPaidTillEmi = emi.getAmountPaidTillCurrentEmi();
        emi.updateEmiWithLumpsum(200);
        assertNotEquals(200, emi.getAmountPaidTillCurrentEmi());
    }

    @Test
    void setEmiToZero() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = borrower.getLoan();
        Emi emi = loan.getEmiList().get(0);
        emi.setEmiToZero((int)loan.getAmount());
        assertEquals(0, emi.getEmiAmount());
    }
}