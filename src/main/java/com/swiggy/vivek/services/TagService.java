package com.swiggy.vivek.services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.swiggy.vivek.persistence.model.Tag;
import com.swiggy.vivek.rest.dto.TagDto;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private MongoDatabase mongoDatabase;

    private MongoCollection<Tag> collection() {
        return mongoDatabase.getCollection("tags", Tag.class);
    }

    public void createTag(String tag) {
        System.out.println("Setting tag: " + tag);
        collection().updateOne(Filters.eq("tag", tag),
                Updates.set("tag", tag),
                (new UpdateOptions()).upsert(true));
    }

    public List<TagDto> findTags(String prefix) {
        Document regexQuery = new Document();
        regexQuery.append("$regex", "^(?)" + Pattern.quote(prefix));
        regexQuery.append("$options", "i");

        Document findQuery = new Document();
        findQuery.append("tag", regexQuery);

        List<Tag> tagEntities = collection().find(findQuery).limit(50).into(new ArrayList<Tag>());
        System.out.println("Result size: " + tagEntities.size());
        return tagEntities.stream().map(tag -> toDto(tag)).collect(Collectors.toList());
    }

    private TagDto toDto(Tag tag) {
        TagDto dto = new TagDto();
        dto.setTag(tag.getTag());
        return dto;
    }

}
