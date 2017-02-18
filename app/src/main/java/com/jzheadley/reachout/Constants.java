package com.jzheadley.reachout;

import com.jzheadley.reachout.models.dataobjects.Borrower;
import com.jzheadley.reachout.models.dataobjects.Leader;
import com.jzheadley.reachout.models.dataobjects.Picture;
import com.jzheadley.reachout.models.dataobjects.Pitch;
import com.jzheadley.reachout.models.dataobjects.Proposal;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;

public class Constants {

    public ArrayList<Borrower> testBorrowers = new ArrayList<>();
    public ArrayList<Leader> testLeaders = new ArrayList<>();

    public Constants() {
        Borrower joe = new Borrower("id2", "Joe Farmer", 12.3425, 13.533535, 264643647, "id1");
        try {
            joe.setProfile_picture(new Picture(25535, "Joe", 12.3425, 13.533535, new URL("http://bit.ly/joespic")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        testBorrowers.add(joe);
        Proposal joeIrrigation = new Proposal(508706863);
        joeIrrigation.setAccountNumber("5380980253");
        joeIrrigation.setAmountBorrowed(50);
        joeIrrigation.setAmountToBeRepayed(75);
        joeIrrigation.setDateFunded(new Date(System.currentTimeMillis()));
        joeIrrigation.setDateRepaid(new Date(System.currentTimeMillis() + 500000));
        joeIrrigation.setLenderAccountNumber("96936708608");
        joeIrrigation.setMonthsOfLoan(5);
        joeIrrigation.setPitch(new Pitch(1512535,"I am a farmer", "Battery", "I'm going to use the battery for my irrigation pump"));
        joeIrrigation.setState(Proposal.STATE_CASH_REPAID);
        try {
            Picture joesCanal = new Picture(25535, "This is my irrigation canal", 12.3425, 13.533535, new URL("http://bit.ly/joescanal"));
            joeIrrigation.getPitch().getPictures().add(joesCanal);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        joe.getProposals().add(joeIrrigation);


        Leader mike = new Leader("id2", "Mullah Mike", 12.3425, 13.533535, 264643647, "id1");
        testLeaders.add(mike);

        try {
            joe.setProfile_picture(new Picture(25535, "Joe", 12.3425, 13.533535, new URL("http://bit.ly/mikesspic")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        mike.getBorrowerIds().add(joe.getPersonId());
        mike.getEndorsedProposals().add(joeIrrigation);

    }

}
