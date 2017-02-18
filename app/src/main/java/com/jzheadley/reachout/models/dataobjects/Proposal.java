package com.jzheadley.reachout.models.dataobjects;

import java.sql.Date;

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

    private Date dateFunded;
    private String lenderAccountNumber = null;

    private Date dateRepaid; //Actual, not due date

    private Pitch pitch;


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
        this.dateFunded = new Date(System.currentTimeMillis());
    }

    public void cashWithdrawn() {
        state = STATE_CASH_WITHDRAWN;
    }

    public void cashRepaid() {
        state = STATE_CASH_REPAID;
        this.dateRepaid = new Date(System.currentTimeMillis());
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

    public Date getDateFunded() {
        return dateFunded;
    }

    public void setDateFunded(Date dateFunded) {
        this.dateFunded = dateFunded;
    }

    public String getLenderAccountNumber() {
        return lenderAccountNumber;
    }

    public void setLenderAccountNumber(String lenderAccountNumber) {
        this.lenderAccountNumber = lenderAccountNumber;
    }

    public Date getDateRepaid() {
        return dateRepaid;
    }

    public void setDateRepaid(Date dateRepaid) {
        this.dateRepaid = dateRepaid;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }


}
