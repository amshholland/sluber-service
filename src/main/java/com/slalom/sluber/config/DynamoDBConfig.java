package com.slalom.sluber.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configures the local DynamoDB connection.
 * Referencing values from resources/application.properties to connect to database
 */
@Configuration
public class DynamoDBConfig {

    // host end point variable from resources/application.properties
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    // Accesskey for Amazon database (doesn't matter for local version)
    @Value("${amazon.aws.accesskey}")
    private String amazonDynamoDBAccessKey;

    // Secretkey for Amazon database
    @Value("${amazon.aws.secretkey}")
    private String amazonDynamoDBSecretKey;


    @Value("${amazon.aws.signingRegion}")
    private String amazonDynamoDBSigningRegion;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonDynamoDBAccessKey, amazonDynamoDBSecretKey)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBSigningRegion))
                .build();
        return new DynamoDBMapper(client, DynamoDBMapperConfig.DEFAULT);
    }
}
