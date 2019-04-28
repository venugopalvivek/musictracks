package com.swiggy.vivek.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DbConfig {

    @Value("${mongodb.hostname}")
    private String hostname;

    @Value("${mongodb.port}")
    private int port;

    @Value("${mongodb.database}")
    private String database;

    @Bean
    public MongoClient getMongoClient() {
        return MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(
                                builder -> builder.hosts(
                                        Arrays.asList(new ServerAddress(hostname, port))
                                ))
                        .build()
        );
    }

    @Bean
    public MongoDatabase getMongoDatabase() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );
        return getMongoClient().getDatabase(database).withCodecRegistry(pojoCodecRegistry);
    }

}
