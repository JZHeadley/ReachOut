package com.jzheadley.reachout.models.services;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.jzheadley.reachout.App;

public class DynamoMapperClient {
    private static DynamoMapperClient ourInstance = new DynamoMapperClient();
    private static CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
        App.get().getApplicationContext(),
        "us-east-1:a48cc8e1-1d83-4b08-b07f-de46cc8c717e",
        Regions.US_EAST_1
    );
    private static AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
    private static DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);

    private DynamoMapperClient() {
    }

    public static DynamoMapperClient getInstance() {
        return ourInstance;
    }

    public static DynamoDBMapper getMapper() {
        return mapper;
    }
}
