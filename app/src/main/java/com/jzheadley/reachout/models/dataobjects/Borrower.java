package com.jzheadley.reachout.models.dataobjects;

import java.util.ArrayList;

/**
 * Created by pjhud on 2/18/2017.
 */

public class Borrower extends Person {
    private String idOfConfirmingLeader;
    private ArrayList<Proposal> proposals;


    public Borrower(String personId, String name, double longitude, double lattitude,
                    int passHash, String idOfConfirmingLeader) {
        this.personId = personId;
        this.name = name;
        this.passHash = passHash;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.profile_picture = null;

        this.idOfConfirmingLeader = idOfConfirmingLeader;
        this.proposals = new ArrayList<>();

    }

    public String getIdOfConfirmingLeader() {
        return idOfConfirmingLeader;
    }

    public void setIdOfConfirmingLeader(String idOfConfirmingLeader) {
        this.idOfConfirmingLeader = idOfConfirmingLeader;
    }

    public ArrayList<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(ArrayList<Proposal> proposals) {
        this.proposals = proposals;
    }
}
