package com.jzheadley.reachout.models.services;


import android.util.Log;

import com.jzheadley.reachout.App;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;
import com.jzheadley.reachout.models.dataobjects.Person;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.constants.TransactionMedium;
import com.reimaginebanking.api.nessieandroidsdk.constants.TransactionType;
import com.reimaginebanking.api.nessieandroidsdk.models.Account;
import com.reimaginebanking.api.nessieandroidsdk.models.Transfer;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;
import com.reimaginebanking.api.nessieandroidsdk.models.PostResponse;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class NessieService {
    private static final String TAG = "NessieService";
    private static NessieClient client = NessieClient.getInstance(App.get().getResources().getString(R.string.nessie_api_key));
    private String date = DateFormat.getDateTimeInstance().format(new Date());

    public void checkFunds() { //check for funding
        //final ArrayList<Proposal> props = new ArrayList<>(); //TODO: we will create a singleton to get you all this;

        //for(int i = 0; i < props.size(); i++)
        for (String prop : ModelSingleton.getInstance().getProposals().keySet())
        {
            final Proposal proposal = ModelSingleton.getInstance().getProposals().get(prop);
            client.ACCOUNT.getAccount(proposal.getAccountNumber(), new NessieResultsListener() {
                @Override
                public void onSuccess(Object result) {
                    Account account = (Account) result;

                    if(account.getBalance() >= proposal.getAmountBorrowed())
                    {

                        client.TRANSFER.getTransfers(proposal.getAccountNumber(), new NessieResultsListener() {
                            @Override
                            public void onSuccess(Object result) {
                                ArrayList<Transfer> transfers = (ArrayList<Transfer>) result;
                                proposal.funded(transfers.get(0).getPayerId());
                            }
                            @Override
                            public void onFailure(NessieError error){
                                Log.d(TAG, "onFailure: "+error);
                            }
                        });
                    }
                }
                @Override
                public void onFailure(NessieError error){
                    Log.d(TAG, "onFailure: "+error);
                }

            });
        }
    }

    public void cashWithdrawl(Proposal proposal, int bankAccountNumber)
    {
        Transfer transfer;
        if(proposal.getState() == 3)
        {
            transfer = new Transfer(getTransferId(), date, "Completed", TransactionType.P2P, TransactionMedium.BALANCE, proposal.getAccountNumber(), Integer.toString(bankAccountNumber), proposal.getAmountBorrowed(), "Proposal to bank");
            proposal.cashWithdrawn();
        }
    }

    public void repay(final Proposal proposal)
    { //check balance of prop, if >= repayment amount send to lender
        client.ACCOUNT.getAccount(proposal.getAccountNumber(), new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                Account account = (Account) result;

                if(account.getBalance() >= proposal.getAmountToBeRepayed())
                {
                        Transfer transfer = new Transfer.Builder()
                            .medium(TransactionMedium.BALANCE)
                            .payeeId(proposal.getLenderAccountNumber())
                            .transactionDate(date)
                            .amount(proposal.getAmountToBeRepayed())
                            .description("Repay to lender")
                            .build();

                    client.TRANSFER.createTransfer(proposal.getAccountNumber(), transfer, new NessieResultsListener() {
                        @Override
                        public void onSuccess(Object result) {
                            PostResponse<Transfer> response = (PostResponse<Transfer>) result;
                            proposal.cashRepaid();
                        }
                        @Override
                        public void onFailure(NessieError error){
                            Log.d(TAG, "onFailure: "+error);
                        }
                    });
                }
            }
            @Override
            public void onFailure(NessieError error){
                Log.d(TAG, "onFailure: "+error);
            }
        });
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
