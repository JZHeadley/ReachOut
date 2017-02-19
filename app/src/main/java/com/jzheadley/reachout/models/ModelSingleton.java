package com.jzheadley.reachout.models;

import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.jzheadley.reachout.models.dataobjects.Person;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.jzheadley.reachout.models.services.DynamoMapperClient;
import com.jzheadley.reachout.models.services.NessieService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ModelSingleton {
    private static final String TAG = "ModelSingleton";
    private static ModelSingleton ourInstance = new ModelSingleton();
    private HashMap<String, Person> people = new HashMap<>();
    private HashMap<String, Proposal> proposals = new HashMap<>();
    private HashSet<Person> newPeople = new HashSet<>();
    private HashSet<Proposal> newProposals = new HashSet<>();
    //private NessieService nessieService;

    private ModelSingleton() {
        synchWithDB();
    }

    public static ModelSingleton getInstance() {
        return ourInstance;
    }

    /*For updates from database */
    public void putPerson(Person person) {
        people.put(person.getPersonId(), person);
    }

    public void putProposal(Proposal proposal) {
        proposals.put(proposal.getProposalId(), proposal);
    }

    /*For newly created people/proposals */
    public void createPerson(Person person) {
        newPeople.add(person);
        putPerson(person);
    }

    public void createProposal(Proposal proposal) {
        newProposals.add(proposal);
        putProposal(proposal);
    }

    /* Getters/Setters */

    public void synchWithDB() {
        (new SynchWithDataBase()).execute();
    }

    public HashMap<String, Person> getPeople() {
        Log.d(TAG, "getPeople: Getting people");
        return people;
    }

    public HashMap<String, Proposal> getProposals() {
        return proposals;
    }

    public ArrayList<Proposal> listProposalsForPerson(Person person) {
        ArrayList<Proposal> result = new ArrayList<>();
        for (String proposalId : person.getProposals()) {
            result.add(proposals.get(proposalId));
        }
        return result;
    }

    private class SynchWithDataBase extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: SYNCHING!");
            for (Person pers : people.values()) {
                DynamoMapperClient.getMapper().save(pers);
            }
            newPeople.clear();
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            PaginatedScanList<Person> result = DynamoMapperClient.getMapper().scan(Person.class, scanExpression);
            result.size();
            for (Person pers : result) {
                putPerson(pers);
            }


            for (Proposal prop : proposals.values()) {
                Log.d(TAG, "doInBackground: creating new proposal");
                if (prop == null) {
                    Log.i(TAG, "doInBackground: Null proposal in newProposals");
                } else {
                    NessieService.createAccount(prop);
                    DynamoMapperClient.getMapper().save(prop);
                }
            }

            newProposals.clear();
            DynamoDBScanExpression propScanExpression = new DynamoDBScanExpression();
            PaginatedScanList<Proposal> propResult = DynamoMapperClient.getMapper().scan(Proposal.class, propScanExpression);
            propResult.size();
            for (Proposal prop : propResult) {
                putProposal(prop);
            }
            NessieService.checkFunds();

            return null;
        }
    }
}
