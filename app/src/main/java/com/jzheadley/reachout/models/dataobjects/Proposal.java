package com.jzheadley.reachout.models.dataobjects;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by pjhud on 2/18/2017.
 */

public class Proposal {
    public static int STATE_INCOMPLETE = 0;
    public static int STATE_SUBMITTED_OFFLINE = 1;
    public static int STATE_SUBMITTED_ONLINE = 2;
    public static int STATE_FUNDED = 3;
    public static int STATE_CASH_WITHDRAWN = 4;
    public static int STATE_CASH_REPAID = 5;

    private int proposalId;
    private int state = STATE_INCOMPLETE;

    private int amountBorrowed = -1;

    private int amountToBeRepayed = -1;
    private int monthsOfLoan = -1;

    private String accountNumber = null;

    private Long dateFunded;
    private String lenderAccountNumber = null;

    private Long dateRepaid; //Actual, not due date

    public String businessDescription = null;
    public String purchaseDescription = null;
    public String planDescription = null;

    public ArrayList<String> pictures = new ArrayList<>();
    public ArrayList<String> endorsingLeaders = new ArrayList<>();


    public Proposal(int proposalId) {
        this.proposalId = proposalId;
    }

    /* State changes */
    public boolean submit_offline() {
        state = STATE_SUBMITTED_OFFLINE;
        return true;
    }

    public void submitted_online(String accountNumber) {
        state = STATE_SUBMITTED_ONLINE;
        this.accountNumber = accountNumber;
    }

    public void funded(String lenderAccountNumber) {
        setLenderAccountNumber(lenderAccountNumber);
        state = STATE_FUNDED;
        this.lenderAccountNumber = lenderAccountNumber;
        this.dateFunded = System.currentTimeMillis();
    }

    public void cashWithdrawn() {
        state = STATE_CASH_WITHDRAWN;
    }

    public void cashRepaid() {
        state = STATE_CASH_REPAID;
        this.dateRepaid = System.currentTimeMillis();
    }


    /* Getters / Setters */
    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getAmountBorrowed() {
        return amountBorrowed;
    }

    public void setAmountBorrowed(int amountBorrowed) {
        this.amountBorrowed = amountBorrowed;
    }

    public int getAmountToBeRepayed() {
        return amountToBeRepayed;
    }

    public void setAmountToBeRepayed(int amountToBeRepayed) {
        this.amountToBeRepayed = amountToBeRepayed;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getMonthsOfLoan() {
        return monthsOfLoan;
    }

    public void setMonthsOfLoan(int monthsOfLoan) {
        this.monthsOfLoan = monthsOfLoan;
    }

    public Long getDateFunded() {
        return dateFunded;
    }

    public void setDateFunded(Long dateFunded) {
        this.dateFunded = dateFunded;
    }

    public String getLenderAccountNumber() {
        return lenderAccountNumber;
    }

    public void setLenderAccountNumber(String lenderAccountNumber) {
        this.lenderAccountNumber = lenderAccountNumber;
    }

    public Long getDateRepaid() {
        return dateRepaid;
    }

    public void setDateRepaid(Long dateRepaid) {
        this.dateRepaid = dateRepaid;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    public String getPurchaseDescription() {
        return purchaseDescription;
    }

    public void setPurchaseDescription(String purchaseDescription) {
        this.purchaseDescription = purchaseDescription;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }

    public ArrayList<String> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<String> pictures) {
        this.pictures = pictures;
    }

    public ArrayList<String> getEndorsingLeaders() {
        return endorsingLeaders;
    }

    public void setEndorsingLeaders(ArrayList<String> endorsingLeaders) {
        this.endorsingLeaders = endorsingLeaders;
    }


}
