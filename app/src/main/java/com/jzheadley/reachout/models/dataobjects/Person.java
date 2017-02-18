package com.jzheadley.reachout.models.dataobjects;

import android.location.Location;

/**
 * Created by pjhud on 2/18/2017.
 */

public class Person {
    protected String personId;
    protected String name;
    protected Location location;
    protected Picture profile_picture;
    protected int passHash;

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Picture getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(Picture profile_picture) {
        this.profile_picture = profile_picture;
    }

    public int getPassHash() {
        return passHash;
    }

    public void setPassHash(int passHash) {
        this.passHash = passHash;
    }

    public boolean isLeader() {
        return (this instanceof Leader);
    }

    public boolean isBorrower() {
        return (this instanceof Borrower);
    }
}
