package com.ledger;

public class Emi {
    private int emiAmount;
    private int amountPaidTillCurrentEmi;
    private int remainingAmountAfterCurrentEmi;
    
    Emi(int emiAmount){
        this.emiAmount = emiAmount;
        this.amountPaidTillCurrentEmi = 0;
    }
    
    public int getEmiAmount() {
        return emiAmount;
    }
    
    public void setEmiAmount(int emiAmount) {
        this.emiAmount = emiAmount;
    }
    
    public int getAmountPaidTillCurrentEmi() {
        return amountPaidTillCurrentEmi;
    }
    
    public void setAmountPaidTillCurrentEmi(int amountPaidTillCurrentEmi) {
        this.amountPaidTillCurrentEmi = amountPaidTillCurrentEmi;
    }
    
    public int getRemainingAmountAfterCurrentEmi() {
        return remainingAmountAfterCurrentEmi;
    }
    
    public void setRemainingAmountAfterCurrentEmi(int remainingAmountAfterCurrentEmi) {
        this.remainingAmountAfterCurrentEmi = remainingAmountAfterCurrentEmi;
    }
    
    public void updateEmiWithLumpsum(int lumpsumAmount) {
        Emi emi = this;
        emi.setAmountPaidTillCurrentEmi(emi.getAmountPaidTillCurrentEmi() + lumpsumAmount);
        emi.setRemainingAmountAfterCurrentEmi(emi.getRemainingAmountAfterCurrentEmi() - lumpsumAmount);
        if(emi.getRemainingAmountAfterCurrentEmi() <= 0){
            emi.setEmiAmount(emiAmount + emi.getRemainingAmountAfterCurrentEmi());
            emi.setAmountPaidTillCurrentEmi(emi.getAmountPaidTillCurrentEmi() + emi.getRemainingAmountAfterCurrentEmi());
            emi.setRemainingAmountAfterCurrentEmi(0);
        }
    }
    
    public void setEmiToZero(int loanAmount){
        this.setEmiAmount(0);
        this.setAmountPaidTillCurrentEmi(loanAmount);
        this.setRemainingAmountAfterCurrentEmi(0);
    }
}
