package com.jzheadley.reachout.models.dataobjects;

import android.os.Parcel;
import android.os.Parcelable;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.ArrayList;

@DynamoDBTable(tableName = "ReachOutPersons")
public class Person implements Parcelable {
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    protected String name = " ";
    private String personId = " ";
    private double longitude = 0;
    private double lattitude = 0;
    private String profile_picture = " ";
    private String passHash = " ";
    private boolean isLeader = false;
    // private String idOfConfirmingLeader = " ";
    private ArrayList<String> proposals;
    public Person() {
        proposals = new ArrayList<>();
        proposals.add(" ");
    }

    public Person(String personId, boolean isLeader, String name, double longitude, double lattitude,
                  String passHash) {
        this.personId = personId;
        this.isLeader = isLeader;
        this.name = name;
        this.passHash = passHash;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.profile_picture = " ";

        //this.idOfConfirmingLeader = idOfConfirmingLeader;
        this.proposals = new ArrayList<>();

    }

    protected Person(Parcel in) {
        this.name = in.readString();
        this.personId = in.readString();
        this.longitude = in.readDouble();
        this.lattitude = in.readDouble();
        this.profile_picture = in.readString();
        this.passHash = in.readString();
        this.isLeader = in.readByte() != 0;
        this.proposals = in.createStringArrayList();
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", personId='" + personId + '\'' +
            ", longitude=" + longitude +
            ", lattitude=" + lattitude +
            ", profile_picture='" + profile_picture + '\'' +
            ", passHash='" + passHash + '\'' +
            ", isLeader=" + isLeader +
            ", proposals=" + proposals +
            '}';
    }

    @DynamoDBHashKey(attributeName = "personId")
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setPerson_id(String personId) {
        this.personId = personId;
    }

    @DynamoDBAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute
    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    @DynamoDBAttribute
    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    @DynamoDBAttribute
    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    @DynamoDBAttribute
    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }


    //public String getIdOfConfirmingLeader() {
    //    return idOfConfirmingLeader;
    //}

    //public void setIdOfConfirmingLeader(String idOfConfirmingLeader) {
    //  this.idOfConfirmingLeader = idOfConfirmingLeader;
    //}

    @DynamoDBAttribute
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<String> getProposals() {
        return proposals;
    }

    public void setProposals(ArrayList<String> proposals) {
        this.proposals = proposals;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.personId);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.lattitude);
        dest.writeString(this.profile_picture);
        dest.writeString(this.passHash);
        dest.writeByte(this.isLeader ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.proposals);
    }
}
