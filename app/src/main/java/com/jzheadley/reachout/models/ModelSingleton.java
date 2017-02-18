package com.jzheadley.reachout.models;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.jzheadley.reachout.models.dataobjects.Person;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.jzheadley.reachout.models.services.DynamoMapperClient;

import java.util.HashMap;
import java.util.HashSet;

public class ModelSingleton {
    private static final String TAG = "ModelSingleton";
    private static ModelSingleton ourInstance = new ModelSingleton();
    private HashMap<String, Person> people = new HashMap<>();
    private HashMap<String, Proposal> proposals = new HashMap<>();
    private HashSet<Person> newPeople = new HashSet<>();
    private HashSet<Proposal> newProposals = new HashSet<>();

    private ModelSingleton() {
    }

    public static ModelSingleton getInstance() {
        return ourInstance;
    }

    /*For updates from database */
    public void addPerson(Person person) {
        people.put(person.getPersonId(), person);
    }

    public void addProposal(Proposal proposal) {
        proposals.put(proposal.getProposalId(), proposal);
    }

    /*For newly created people/proposals */
    public void createPerson(Person person) {
        newPeople.add(person);
        addPerson(person);
    }

    public void createProposal(Proposal proposal) {
        newProposals.add(proposal);
        addProposal(proposal);
    }

    /* Getters/Setters */

    public void synchWithDB() {
        (new SynchWithDataBase()).execute();
    }

    public HashMap<String, Person> getPeople() {
        return people;
    }

    public HashMap<String, Proposal> getProposals() {
        return proposals;
    }

    private class SynchWithDataBase extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            for (Person pers : newPeople) {
                DynamoMapperClient.getMapper().save(pers);
            }
            newPeople.clear();
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            PaginatedScanList<Person> result = DynamoMapperClient.getMapper().scan(Person.class, scanExpression);
            result.size();
            for (Person pers : result) {
                addPerson(pers);
            }


            for (Proposal prop : newProposals) {
                DynamoMapperClient.getMapper().save(prop);
            }

            newProposals.clear();
            DynamoDBScanExpression propScanExpression = new DynamoDBScanExpression();
            PaginatedScanList<Proposal> propResult = DynamoMapperClient.getMapper().scan(Proposal.class, propScanExpression);
            propResult.size();
            for (Proposal prop : propResult) {
                addProposal(prop);
            }
            return null;
        }
    }
}
