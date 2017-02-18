package com.jzheadley.reachout.models.dataobjects;

import java.util.ArrayList;


public class Person {
    protected String personId;
    protected String name;
    protected double longitude;
    protected double lattitude;
    protected String profile_picture;
    protected int passHash;
    private boolean isLeader;
    private String idOfConfirmingLeader;
    private ArrayList<String> proposals;


    public Person(String personId, boolean isLeader, String name, double longitude, double lattitude,
                    int passHash, String idOfConfirmingLeader) {
        this.personId = personId;
        this.isLeader = isLeader;
        this.name = name;
        this.passHash = passHash;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.profile_picture = null;

        this.idOfConfirmingLeader = idOfConfirmingLeader;
        this.proposals = new ArrayList<>();

    }

    public String getPersonId() {
        return personId;
    }

    public void setPerson_id(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public int getPassHash() {
        return passHash;
    }

    public void setPassHash(int passHash) {
        this.passHash = passHash;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }


    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }




    public String getIdOfConfirmingLeader() {
        return idOfConfirmingLeader;
    }

    public void setIdOfConfirmingLeader(String idOfConfirmingLeader) {
        this.idOfConfirmingLeader = idOfConfirmingLeader;
    }

    public ArrayList<String> getProposals() {
        return proposals;
    }

    public void setProposals(ArrayList<String> proposals) {
        this.proposals = proposals;
    }
}
