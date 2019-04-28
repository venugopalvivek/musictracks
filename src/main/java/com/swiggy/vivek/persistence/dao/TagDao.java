package com.swiggy.vivek.persistence.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.swiggy.vivek.persistence.model.Tag;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class TagDao {

    @Autowired
    private MongoDatabase mongoDatabase;

    private MongoCollection<Tag> collection() {
        return mongoDatabase.getCollection("tags", Tag.class);
    }

    public void createTag(String tag) {
        collection().updateOne(Filters.eq("tag", tag),
                Updates.set("tag", tag),
                (new UpdateOptions()).upsert(true));
    }

    public List<Tag> findTags(String prefix) {
        Document regexQuery = new Document();
        regexQuery.append("$regex", "^(?)" + Pattern.quote(prefix));
        regexQuery.append("$options", "i");

        Document findQuery = new Document();
        findQuery.append("tag", regexQuery);

        return collection().find(findQuery).limit(50).into(new ArrayList<Tag>());
    }

}
