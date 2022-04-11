package com.ledger;

import java.util.ArrayList;
import java.util.List;

public class Loan {
    private static final int NO_OF_MONTHS_IN_A_YEAR = 12;
    private static int id = 1;
    private int loanId;
    private double principal;
    private double interestRate;
    private double noOfYears;
    private double amount;
    private int noOfEmi;
    private List<Emi> emiList;
    
    Loan(double principal, double interestRate, double noOfYears){
        this.loanId = getUniqueLoanId();
        this.principal = principal;
        this.interestRate = interestRate;
        this.noOfYears = noOfYears;
        emiList = new ArrayList<>();
        calculateAmount();
        calculateEmi();
    }
    
    
    public double getAmount() {
        return amount;
    }
    
    public int getNoOfEmi() {
        return noOfEmi;
    }
    
    public List<Emi> getEmiList() {
        return emiList;
    }
    
    private void calculateEmi() {
        this.noOfEmi = (int) this.noOfYears * NO_OF_MONTHS_IN_A_YEAR;
        int emiAmount = (int)Math.ceil(this.amount / this.noOfEmi);
        Emi emi = new Emi(emiAmount);
        emi.setAmountPaidTillCurrentEmi(0);
        emi.setRemainingAmountAfterCurrentEmi((int)amount);
        emiList.add(emi);
        for(int i=1; i<=noOfEmi; i++){
            emi = new Emi(emiAmount);
            emi.setAmountPaidTillCurrentEmi(emiAmount * (i));
            emi.setRemainingAmountAfterCurrentEmi((int)amount - emi.getAmountPaidTillCurrentEmi());
            if(emi.getRemainingAmountAfterCurrentEmi() <= 0){
                emi.setEmiAmount(emiAmount + emi.getRemainingAmountAfterCurrentEmi());
                emi.setAmountPaidTillCurrentEmi(emi.getAmountPaidTillCurrentEmi() + emi.getRemainingAmountAfterCurrentEmi());
                emi.setRemainingAmountAfterCurrentEmi(0);
            }
            emiList.add(emi);
        }
    }
    
    private void calculateAmount() {
        this.amount = (int)Math.ceil(principal + (principal * interestRate * noOfYears)/100);
    }
    
    private int getUniqueLoanId() {
        return id++;
    }
    
    public int getLastEmiNumber(int emiNumber) {
        boolean lumpsumFound = false;
        List<Emi> emiList = this.emiList;
        int emiAmount = emiList.get(0).getEmiAmount();
        for(int i=0; i<=emiNumber; i++){
            Emi emi = emiList.get(i);
            if((emiAmount * i) < emi.getAmountPaidTillCurrentEmi()){
                lumpsumFound = true;
                break;
            }
        }
        if(!lumpsumFound){
            return emiList.size() - 1;
        }else {
            for (int i = 0; i < emiList.size(); i++) {
                Emi emi = emiList.get(i);
                if (emi.getRemainingAmountAfterCurrentEmi() == 0) {
                    return i;
                }
            }
        }
        return 0;
    }
    
    public int getAmountPaid(int emiNumber){
        return emiList.get(emiNumber).getAmountPaidTillCurrentEmi();
    }
    
    public void updateRemainingEmis(int lumpsumAmount, int emiNumber) {
        Loan loan = this;
        List<Emi> emiList = loan.getEmiList();
        int lastEmi = 0;
        for(int i=emiNumber; i<=loan.getNoOfEmi(); i++){
            Emi emi = emiList.get(i);
            emi.updateEmiWithLumpsum(lumpsumAmount);
            if(emi.getRemainingAmountAfterCurrentEmi() == 0){
                lastEmi = i;
                break;
            }
        }
        for(int i=lastEmi+1; i<=loan.getNoOfEmi(); i++){
            Emi emi = emiList.get(i);
            emi.setEmiToZero((int)loan.getAmount());
        }
    }
}
