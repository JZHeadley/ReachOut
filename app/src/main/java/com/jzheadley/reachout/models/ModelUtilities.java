package com.jzheadley.reachout.models;

import com.jzheadley.reachout.models.dataobjects.Proposal;

import java.util.List;

/**
 * Created by pjhud on 2/18/2017.
 */

public class ModelUtilities {

    public static int creditScore(List<Proposal> proposals) {
        int creditScore = 0;
        for (Proposal prop : proposals) {
            switch (prop.getState()) {
                case Proposal.STATE_FUNDED:
                    creditScore -= prop.getAmountToBeRepayed() / 5;
                case Proposal.STATE_CASH_WITHDRAWN:
                    creditScore -= prop.getAmountToBeRepayed() / 5;
                case Proposal.STATE_CASH_REPAID:
                    if (dueDate(prop) > prop.getDateFunded()) {
                        creditScore += prop.getAmountToBeRepayed() / 10;
                    } else {
                        creditScore -= prop.getAmountToBeRepayed() / 10;
                    }
                    break;
                default:
            }
        }
        return creditScore;
    }

    public static long dueDate(Proposal proposal) {
        return proposal.getDateFunded() + ((long)proposal.getMonthsOfLoan() * 2592000000L);
    }

}
