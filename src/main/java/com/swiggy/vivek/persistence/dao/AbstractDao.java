package com.swiggy.vivek.persistence.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> {

    @Autowired
    private MongoDatabase mongoDatabase;

    private final String collectionName;
    private final Class<T> type;

    public AbstractDao(String collectionName, Class<T> type) {
        this.collectionName = collectionName;
        this.type = type;
    }

    protected MongoCollection<T> collection() {
        return mongoDatabase.getCollection(collectionName, this.type);
    }

}
