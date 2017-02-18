package com.jzheadley.reachout.models.dataobjects;

import com.jzheadley.reachout.models.dataobjects.Picture;

import java.util.ArrayList;

/**
 * Created by pjhud on 2/18/2017.
 */

public class Pitch {

    public int proposalId;
    public String businessDescription;
    public String purchaseDescription;
    public String planDescription;

    public ArrayList<Picture> pictures;
    public ArrayList<Integer> endorsingLeaders;

    public Pitch(int proposalId, String businessDescription,
                 String purchaseDescription, String planDescription) {
        this.proposalId = proposalId;
        this.businessDescription = businessDescription;
        this.purchaseDescription = purchaseDescription;
        this.planDescription = planDescription;
        this.pictures = new ArrayList<>();
        this.endorsingLeaders = new ArrayList<>();
    }

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
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

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    public ArrayList<Integer> getEndorsingLeaders() {
        return endorsingLeaders;
    }

    public void setEndorsingLeaders(ArrayList<Integer> endorsingLeaders) {
        this.endorsingLeaders = endorsingLeaders;
    }
}
