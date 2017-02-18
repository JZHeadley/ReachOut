package com.jzheadley.reachout;

import com.jzheadley.reachout.models.dataobjects.Person;
import com.jzheadley.reachout.models.dataobjects.Proposal;

import java.util.HashSet;
import java.util.Set;

public class Constants {

    public static final String IMGUR_CLIENT_ID = "ce54a4872a6b508";
    public static final String IMGUR_SECRET = "87b0d52abe1f00675653528a8616664b018774b0";
    public Set<Person> people = new HashSet<>();
    public Set<Proposal> proposals = new HashSet<>();

    public Constants() {
        Person joe = new Person("id2", false, "Joe Farmer", 12.3425, 13.533535, "264643647");
        joe.setProfile_picture("http://imgur.com/oazRdv1");

        people.add(joe);
        Proposal joeIrrigation = new Proposal("508706863");
        joeIrrigation.setState(Proposal.STATE_CASH_REPAID);
        joeIrrigation.setAccountNumber("5380980253");
        joeIrrigation.setAmountBorrowed(50);
        joeIrrigation.setAmountToBeRepayed(75);
        joeIrrigation.setDateFunded(System.currentTimeMillis());
        joeIrrigation.setDateRepaid(System.currentTimeMillis() + 500000);
        joeIrrigation.setLenderAccountNumber("96936708608");
        joeIrrigation.setMonthsOfLoan(5);
        joeIrrigation.setBusinessDescription("I am a farmer");
        joeIrrigation.setPurchaseDescription("Battery");
        joeIrrigation.setPlanDescription("I'm going to use the battery for my irrigation pump");

        joeIrrigation.getPictures().add("http://bit.ly/joescanal");

        proposals.add(joeIrrigation);
        joe.getProposals().add(joeIrrigation.getProposalId());


        Person mike = new Person("id2", true, "Mullah Mike", 12.3425, 13.533535, "264643647");
        people.add(mike);

        mike.setProfile_picture("http://bit.ly/mikesspic");

        mike.getProposals().add(joeIrrigation.getProposalId());
        joeIrrigation.getEndorsingLeaders().add(mike.getPersonId());

    }

}
