package com.ledger;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    
    @Test
    void getBankName() {
        Bank bank = new Bank("NAVI");
        assertEquals("NAVI", bank.getBankName());
    }
    
    @Test
    void getBorrowerMap() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Map<String, Borrower> expectedMap = new HashMap<>();
        expectedMap.put("Prince", bank.getBorrowerMap().get("Prince"));
        assertEquals(expectedMap, bank.getBorrowerMap());
    }
    
    @Test
    void sanctionLoan() {
        Bank bank = new Bank("NAVI");
        Borrower borrower = new Borrower("Prince");
        bank.sanctionLoan(borrower, 1000, 10, 2);
        Loan loan = bank.getBorrowerMap().get("Prince").getLoan();
        assertEquals(1200, loan.getAmount());
    }
}