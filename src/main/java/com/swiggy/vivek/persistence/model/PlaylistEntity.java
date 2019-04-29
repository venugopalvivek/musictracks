package com.swiggy.vivek.persistence.model;

import org.bson.types.ObjectId;

import java.util.List;

public final class PlaylistEntity {
    private ObjectId id;
    private String name;
    private String description;
    private List<String> songIds;
    private List<String> tags;
    private long likes;
    private long playCount;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<String> songIds) {
        this.songIds = songIds;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(long playCount) {
        this.playCount = playCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaylistEntity playlistEntity = (PlaylistEntity) o;

        if (getId() != null ? !getId().equals(playlistEntity.getId()) : playlistEntity.getId() != null) {
            return false;
        }
        if (getName() != null ? !getName().equals(playlistEntity.getName()) : playlistEntity.getName() != null) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(playlistEntity.getDescription()) : playlistEntity.getDescription() != null) {
            return false;
        }
        if (getSongIds() != null ? !getSongIds().equals(playlistEntity.getSongIds()) : playlistEntity.getSongIds() != null) {
            return false;
        }
        if (getTags() != null ? !getTags().equals(playlistEntity.getTags()) : playlistEntity.getTags() != null) {
            return false;
        }
        if (getLikes() != playlistEntity.getLikes()) {
            return false;
        }
        if (getPlayCount() != playlistEntity.getPlayCount()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getSongIds() != null ? getSongIds().hashCode() : 0);
        result = 31 * result + (getTags() != null ? getTags().hashCode() : 0);
        result = 31 * result + new Long(getLikes()).hashCode();
        result = 31 * result + new Long(getPlayCount()).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PlayList{"
                + "id='" + id + "'"
                + ", name='" + name + "'"
                + ", description='" + description + "'"
                + "}";
    }

}
