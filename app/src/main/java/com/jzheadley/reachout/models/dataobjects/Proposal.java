package com.jzheadley.reachout.models.dataobjects;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pjhud on 2/18/2017.
 */
@DynamoDBTable(tableName = "Proposal")
public class Proposal {
    public static final int STATE_INCOMPLETE = 0;
    public static final int STATE_SUBMITTED_OFFLINE = 1;
    public static final int STATE_SUBMITTED_ONLINE = 2;
    public static final int STATE_FUNDED = 3;
    public static final int STATE_CASH_WITHDRAWN = 4;
    public static final int STATE_CASH_REPAID = 5;
    public String businessDescription = null;
    public String purchaseDescription = null;
    public String planDescription = null;
    public Set<String> pictures = new HashSet<>();
    public Set<String> endorsingLeaders = new HashSet<>();
    private String proposalId;
    private String personId;
    private int state = STATE_INCOMPLETE;
    private int amountBorrowed = -1;
    private int amountToBeRepayed = -1;
    private int monthsOfLoan = -1;
    private String accountNumber = null;
    private Long dateFunded;
    private String lenderAccountNumber = null;
    private Long dateRepaid; //Actual, not due date


    public Proposal(String proposalId) {
        this.proposalId = proposalId;
    }

    public Proposal() {

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
    @DynamoDBHashKey(attributeName = "proposalId")
    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    @DynamoDBAttribute
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @DynamoDBAttribute
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @DynamoDBAttribute
    public int getAmountBorrowed() {
        return amountBorrowed;
    }

    public void setAmountBorrowed(int amountBorrowed) {
        this.amountBorrowed = amountBorrowed;
    }

    @DynamoDBAttribute
    public int getAmountToBeRepayed() {
        return amountToBeRepayed;
    }

    public void setAmountToBeRepayed(int amountToBeRepayed) {
        this.amountToBeRepayed = amountToBeRepayed;
    }


    @DynamoDBAttribute
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @DynamoDBAttribute
    public int getMonthsOfLoan() {
        return monthsOfLoan;
    }

    public void setMonthsOfLoan(int monthsOfLoan) {
        this.monthsOfLoan = monthsOfLoan;
    }

    @DynamoDBAttribute
    public Long getDateFunded() {
        return dateFunded;
    }

    public void setDateFunded(Long dateFunded) {
        this.dateFunded = dateFunded;
    }

    @DynamoDBAttribute
    public String getLenderAccountNumber() {
        return lenderAccountNumber;
    }

    public void setLenderAccountNumber(String lenderAccountNumber) {
        this.lenderAccountNumber = lenderAccountNumber;
    }

    @DynamoDBAttribute
    public Long getDateRepaid() {
        return dateRepaid;
    }

    public void setDateRepaid(Long dateRepaid) {
        this.dateRepaid = dateRepaid;
    }

    @DynamoDBAttribute
    public String getBusinessDescription() {
        return businessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    @DynamoDBAttribute
    public String getPurchaseDescription() {
        return purchaseDescription;
    }

    public void setPurchaseDescription(String purchaseDescription) {
        this.purchaseDescription = purchaseDescription;
    }

    @DynamoDBAttribute
    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }

    @DynamoDBAttribute
    public Set<String> getPictures() {
        return pictures;
    }

    public void setPictures(HashSet<String> pictures) {
        this.pictures = pictures;
    }

    @DynamoDBAttribute
    public Set<String> getEndorsingLeaders() {
        return endorsingLeaders;
    }

    public void setEndorsingLeaders(HashSet<String> endorsingLeaders) {
        this.endorsingLeaders = endorsingLeaders;
    }


}
