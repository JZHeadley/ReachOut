package com.jzheadley.reachout.models.dataobjects;

import java.util.ArrayList;

/**
 * Created by pjhud on 2/18/2017.
 */

public class Leader extends Person {
    private String idOfConfirmingNGO;
    private ArrayList<String> borrowerIds;
    private ArrayList<Proposal> endorsedProposals;

    public Leader(String personId, String name, double longitude, double lattitude,
                  int passHash, String idOfConfirmingNGO) {
        this.personId = personId;
        this.name = name;
        this.passHash = passHash;

        this.longitude = longitude;
        this.lattitude = lattitude;

        this.profile_picture = null;

        this.idOfConfirmingNGO = idOfConfirmingNGO;
        this.borrowerIds = new ArrayList<>();
        this.endorsedProposals = new ArrayList<>();
    }

    public String getIdOfConfirmingNGO() {
        return idOfConfirmingNGO;
    }

    public void setIdOfConfirmingNGO(String idOfConfirmingNGO) {
        this.idOfConfirmingNGO = idOfConfirmingNGO;
    }

    public ArrayList<String> getBorrowerIds() {
        return borrowerIds;
    }

    public void setBorrowerIds(ArrayList<String> borrowerIds) {
        this.borrowerIds = borrowerIds;
    }

    public ArrayList<Proposal> getEndorsedProposals() {
        return endorsedProposals;
    }

    public void setEndorsedProposals(ArrayList<Proposal> endorsedProposals) {
        this.endorsedProposals = endorsedProposals;
    }
}
