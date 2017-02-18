package com.jzheadley.reachout;

import com.jzheadley.reachout.models.dataobjects.Borrower;
import com.jzheadley.reachout.models.dataobjects.Leader;
import com.jzheadley.reachout.models.dataobjects.Picture;
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
        joe.setProfile_picture("http://bit.ly/mikesspic");

        testBorrowers.add(joe);
        Proposal joeIrrigation = new Proposal(508706863);
        joeIrrigation.setState(Proposal.STATE_CASH_REPAID);
        joeIrrigation.setAccountNumber("5380980253");
        joeIrrigation.setAmountBorrowed(50);
        joeIrrigation.setAmountToBeRepayed(75);
        joeIrrigation.setDateFunded(new Date(System.currentTimeMillis()));
        joeIrrigation.setDateRepaid(new Date(System.currentTimeMillis() + 500000));
        joeIrrigation.setLenderAccountNumber("96936708608");
        joeIrrigation.setMonthsOfLoan(5);
        joeIrrigation.setBusinessDescription("I am a farmer");
        joeIrrigation.setPurchaseDescription("Battery");
        joeIrrigation.setPlanDescription("I'm going to use the battery for my irrigation pump");

        joeIrrigation.getPictures().add("http://bit.ly/joescanal");
        joe.getProposals().add(joeIrrigation);


        Leader mike = new Leader("id2", "Mullah Mike", 12.3425, 13.533535, 264643647, "id1");
        testLeaders.add(mike);

        mike.setProfile_picture("http://bit.ly/mikesspic");

        mike.getBorrowerIds().add(joe.getPersonId());
        mike.getEndorsedProposals().add(joeIrrigation);
        joeIrrigation.getEndorsingLeaders().add(mike.getPersonId());

    }

}
