package com.jzheadley.reachout.models.dataobjects;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by pjhud on 2/18/2017.
 */

public class Borrower extends Person {
    private int idOfConfirmingLeader;
    private ArrayList<Proposal> proposals;


    public Borrower(int personId, String name, Location location,
                    int passHash, int idOfConfirmingLeader) {
        this.personId = personId;
        this.name = name;
        this.passHash = passHash;

        this.location = location;
        this.profile_picture = null;

        this.idOfConfirmingLeader = idOfConfirmingLeader;
        this.proposals = new ArrayList<>();

    }

    public int getIdOfConfirmingLeader() {
        return idOfConfirmingLeader;
    }

    public void setIdOfConfirmingLeader(int idOfConfirmingLeader) {
        this.idOfConfirmingLeader = idOfConfirmingLeader;
    }

    public ArrayList<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(ArrayList<Proposal> proposals) {
        this.proposals = proposals;
    }
}
