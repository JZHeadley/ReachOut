package com.jzheadley.reachout.models.services;


import com.jzheadley.reachout.App;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.reimaginebanking.api.nessieandroidsdk.constants.TransactionMedium;
import com.reimaginebanking.api.nessieandroidsdk.constants.TransactionType;
import com.reimaginebanking.api.nessieandroidsdk.models.Transfer;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class NessieService {
    private static NessieClient client = NessieClient.getInstance(App.get().getResources().getString(R.string.nessie_api_key));
    private String date = DateFormat.getDateTimeInstance().format(new Date());


    public void fundProposal(Proposal proposal, int lenderAccountNumber) { //check for funding
        Transfer transfer;
        if (proposal.getState() == 2) {
            transfer = new Transfer(getTransferId(), date, "Completed", TransactionType.P2P, TransactionMedium.BALANCE, Integer.toString(lenderAccountNumber), Integer.toString(proposal.getAccountNumber()), proposal.getAmountBorrowed(), "Lender to proposal");
            proposal.funded(lenderAccountNumber);
        }
    }

    public void reciveFunds(Proposal proposal, int bankAccountNumber)
    {
        Transfer transfer;
        if(proposal.getState() == 3)
        {
            transfer = new Transfer(getTransferId(), date, "Completed", TransactionType.P2P, TransactionMedium.BALANCE, Integer.toString(proposal.getAccountNumber()), Integer.toString(bankAccountNumber), proposal.getAmountBorrowed(), "Proposal to bank");
            proposal.cashWithdrawn();
        }
    }

    public void repay(Proposal proposal, int bankAccountNumber)
    { //check
        Transfer transfer;
        if(proposal.getState() == 4)
        {
            transfer = new Transfer(getTransferId(), date, "Completed", TransactionType.P2P, TransactionMedium.BALANCE, Integer.toString(bankAccountNumber), Integer.toString(proposal.getLenderAccountNumber()), proposal.getAmountToBeRepayed(), "Bank to lender");
            proposal.cashRepaid();
        }
    }

    private String getTransferId(){
        String transferId;
        int p1,p2,p3;
        Random random = new Random();
        p1 = random.nextInt(899999999)+100000000;
        p2 = random.nextInt(899999999)+100000000;
        p3 = random.nextInt(899999)+100000;
        transferId = Integer.toString(p1)+Integer.toString(p2)+Integer.toString(p3);
        return transferId;
    }

    private NessieService() {
    }

    public static NessieClient getInstance() {
        return client;
    }
}
