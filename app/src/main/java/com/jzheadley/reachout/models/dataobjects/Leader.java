package com.jzheadley.reachout.models.dataobjects;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by pjhud on 2/18/2017.
 */

public class Leader extends Person {
    private int idOfConfirmingNGO;
    private ArrayList<Integer> borrowerIds;
    private ArrayList<Proposal> endorsedProposals;

    public Leader(String personId, String name, Location location,
                  int passHash, int idOfConfirmingNGO) {
        this.personId = personId;
        this.name = name;
        this.passHash = passHash;

        this.location = location;
        this.profile_picture = null;

        this.idOfConfirmingNGO = idOfConfirmingNGO;
        this.borrowerIds = new ArrayList<>();
        this.endorsedProposals = new ArrayList<>();
    }

    public int getIdOfConfirmingNGO() {
        return idOfConfirmingNGO;
    }

    public void setIdOfConfirmingNGO(int idOfConfirmingNGO) {
        this.idOfConfirmingNGO = idOfConfirmingNGO;
    }

    public ArrayList<Integer> getBorrowerIds() {
        return borrowerIds;
    }

    public void setBorrowerIds(ArrayList<Integer> borrowerIds) {
        this.borrowerIds = borrowerIds;
    }

    public ArrayList<Proposal> getEndorsedProposals() {
        return endorsedProposals;
    }

    public void setEndorsedProposals(ArrayList<Proposal> endorsedProposals) {
        this.endorsedProposals = endorsedProposals;
    }
}
