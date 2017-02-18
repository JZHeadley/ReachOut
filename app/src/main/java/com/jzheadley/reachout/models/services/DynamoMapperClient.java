package com.jzheadley.reachout.models.services;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapperConfig;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.jzheadley.reachout.App;

public class DynamoMapperClient {
    private static DynamoMapperClient ourInstance = new DynamoMapperClient();
    private static CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
        App.get().getApplicationContext(),
        "us-east-1:00617083-9504-4800-9a90-5f8690a1e95e", // Identity Pool ID
        Regions.US_EAST_1 // Region
    );
    private static AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
    private static DynamoDBMapper mapper = new DynamoDBMapper(ddbClient, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.CLOBBER));

    private DynamoMapperClient() {
    }

    public static DynamoMapperClient getInstance() {
        return ourInstance;
    }

    public static DynamoDBMapper getMapper() {
        return mapper;
    }
}
