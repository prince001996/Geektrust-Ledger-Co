package com.ledger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<String, Bank> bankRepo = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
	    File file = new File(args[0]);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
    
        String line = bufferedReader.readLine();
        while(line != null){
            String[] inputBlocks = line.split(" ");
            String operation = inputBlocks[0];
            if("LOAN".equals(operation)){
                loan(inputBlocks);
            }
            if("BALANCE".equals(operation)){
                balance(inputBlocks);
            }
            if("PAYMENT".equals(operation)){
                payment(inputBlocks);
            }
            line = bufferedReader.readLine();
        }
    }
    
    private static void payment(String[] inputBlocks){
        String bankName = inputBlocks[1];
        String borrowerName = inputBlocks[2];
        int lumpsumAmount = Integer.valueOf(inputBlocks[3]);
        int emiNumber = Integer.valueOf(inputBlocks[4]);
        //find the bank, the borrower and update Emi if needed
        Bank bank = bankRepo.get(bankName);
        Borrower borrower = bank.getBorrowerMap().get(borrowerName);
        Loan loan = borrower.getLoan();
        loan.updateRemainingEmis(lumpsumAmount, emiNumber);
    }
    
    private static void balance(String[] inputBlocks){
        String bankName = inputBlocks[1];
        String borrowerName = inputBlocks[2];
        int emiNumber = Integer.valueOf(inputBlocks[3]);
        //find the loan for the borrower in the bank
        Bank bank = bankRepo.get(bankName);
        Borrower borrower = bank.getBorrowerMap().get(borrowerName);
        Loan loan = borrower.getLoan();
        printBalanceDetails(bankName, borrowerName, loan, emiNumber);
    }
    
    private static void loan(String[] inputBlocks){
        String bankName = inputBlocks[1];
        String borrowerName = inputBlocks[2];
        double principal = Double.valueOf(inputBlocks[3]);
        double noOfYears = Double.valueOf(inputBlocks[4]);
        double interestRate = Double.valueOf(inputBlocks[5]);
        Bank bank = bankRepo.getOrDefault(bankName, new Bank(bankName));
        bankRepo.put(bankName, bank);
        Borrower borrower = new Borrower(borrowerName);
        borrower.applyForLoan(bank, principal, interestRate, noOfYears);
    }
    
    private static void printBalanceDetails(String bankName, String borrowerName, Loan loan, int emiNumber){
        System.out.println(bankName + " " + borrowerName + " " + loan.getAmountPaid(emiNumber) + " " + (loan.getLastEmiNumber(emiNumber) - emiNumber));
    }
    
}
