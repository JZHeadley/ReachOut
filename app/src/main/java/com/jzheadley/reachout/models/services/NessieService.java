package com.jzheadley.reachout.models.services;


import android.util.Log;

import com.jzheadley.reachout.App;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;
import com.jzheadley.reachout.models.dataobjects.Person;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.constants.AccountType;
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
    //private String date = DateFormat.getDateTimeInstance().format(new Date());
    //private String accountNumber;

    public static void createAccount(final Proposal proposal){
        Log.d(TAG, "createAccount: Called");
        Account account = new Account.Builder()
                .balance(0)
                .nickname(proposal.getPersonId())
                .type(AccountType.CHECKING)
                .rewards(0)
                .accountNumber(getAccountId())
                .build();

        client.ACCOUNT.createAccount(App.get().getString(R.string.customer_id), account, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                PostResponse<Account> response = (PostResponse<Account>) result;
                Account newAccount = response.getObjectCreated();
                Log.d(TAG, "createAccount/onSuccess:  accountNumber: " + newAccount.getId());
                proposal.submitted_online(newAccount.getId());
            }
            @Override
            public void onFailure(NessieError error){
                Log.d(TAG, "(CreateAccount onFailure1: "+error.getMessage() + "\n" + error.toString());
            }
        });
    }

    public static void checkFunds() { //check for funding
        Log.d(TAG, "checkFunds: Called");
        for (String prop : ModelSingleton.getInstance().getProposals().keySet())
        {
            final Proposal proposal = ModelSingleton.getInstance().getProposals().get(prop);
            Log.d(TAG, "checkFunds: proposalDesc/state/account:" + proposal.getBusinessDescription() + "/" + proposal.getState() + "/" + proposal.getAccountNumber());
            client.ACCOUNT.getAccount(proposal.getAccountNumber(), new NessieResultsListener() {
                @Override
                public void onSuccess(Object result) {
                    Account account = (Account) result;
                    Log.d(TAG, "onSuccess: balance:" + account.getBalance());
                    if(proposal.getState() == Proposal.STATE_SUBMITTED_ONLINE
                       &&account.getBalance() >= proposal.getAmountBorrowed())
                    {
                        Log.d(TAG, "onSuccess: Balance increased");
                        client.TRANSFER.getTransfers(proposal.getAccountNumber(), new NessieResultsListener() {
                            @Override
                            public void onSuccess(Object result) {

                                ArrayList<Transfer> transfers = (ArrayList<Transfer>) result;
                                Log.d(TAG, "onSuccess: Funding!");
                                proposal.funded(transfers.get(0).getPayerId());
                                Log.d(TAG, "onSuccess: state should be 3" + proposal.getState());

                            }
                            @Override
                            public void onFailure(NessieError error){
                                Log.d(TAG, "onFailure2: "+error.getMessage());
                            }
                        });
                    }
                }
                @Override
                public void onFailure(NessieError error){
                    Log.d(TAG, "onFailure3: "+error.getMessage());
                }

            });
        }
    }

    public static void cashWithdrawl(Proposal proposal, int bankAccountNumber)
    {
        Transfer transfer = new Transfer.Builder()
                .medium(TransactionMedium.BALANCE)
                .payeeId(Integer.toString(bankAccountNumber))
                .transactionDate(DateFormat.getDateTimeInstance().format(new Date()))
                .amount(proposal.getAmountBorrowed())
                .description("Money from proposal account to bank for withdrawl")
                .build();

        client.TRANSFER.createTransfer(proposal.getAccountNumber(), transfer, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                PostResponse<Transfer> response = (PostResponse<Transfer>) result;
            }
            @Override
            public void onFailure(NessieError error){
                Log.d(TAG, "onFailure: "+error);
            }
        });
    }

    public static void repay(final Proposal proposal)
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
                            .transactionDate(DateFormat.getDateTimeInstance().format(new Date()))
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

    private static String getTransferId(){
        String transferId;
        int p1,p2,p3;
        Random random = new Random();
        p1 = random.nextInt(899999999)+100000000;
        p2 = random.nextInt(899999999)+100000000;
        p3 = random.nextInt(899999)+100000;
        transferId = Integer.toString(p1)+Integer.toString(p2)+Integer.toString(p3);
        return transferId;
    }

    private static String getAccountId(){
        String accountId;
        int p1,p2;
        Random random = new Random();
        p1 = random.nextInt(899999999)+100000000;
        p2 = random.nextInt(8999999)+1000000;
        accountId = Integer.toString(p1)+Integer.toString(p2);
        return accountId;
    }

   // private NessieService() {
    //}


}
