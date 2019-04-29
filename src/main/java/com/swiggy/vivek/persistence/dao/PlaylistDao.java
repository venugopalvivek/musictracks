package com.swiggy.vivek.persistence.dao;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.swiggy.vivek.exceptions.MusicTracksException;
import com.swiggy.vivek.persistence.model.PlaylistEntity;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaylistDao extends AbstractDao<PlaylistEntity> {

    private static final int PAGE_SIZE = 15;

    public PlaylistDao() {
        super("playlists", PlaylistEntity.class);
    }

    public PlaylistEntity createPlaylist(PlaylistEntity request) {
        collection().insertOne(request);
        return request;
    }

    public void deletePlaylist(String id) {
        collection().findOneAndDelete(Filters.eq("_id", new ObjectId(id)));
    }

    public PlaylistEntity find(String id) throws MusicTracksException {
        return collection().find(Filters.eq("_id", new ObjectId(id)))
                .into(new ArrayList<PlaylistEntity>()).get(0);
    }

    public List<PlaylistEntity> search(List<String> tags, int pageNum) {
        return collection().find(Filters.in("tags", tags))
                .sort(Sorts.orderBy(Sorts.descending("likes", "playCount")))
                .skip(PAGE_SIZE * (pageNum-1))
                .limit(PAGE_SIZE)
                .into(new ArrayList<PlaylistEntity>());
    }

}
