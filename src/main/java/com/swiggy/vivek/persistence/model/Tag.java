package com.swiggy.vivek.persistence.model;

import org.bson.types.ObjectId;

public final class Tag {
    private ObjectId id;
    private String tag;

    public Tag() {}

    public Tag(final String tag) {
        this.tag = tag;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (getId() != null ? !getId().equals(tag.getId()) : tag.getId() != null) {
            return false;
        }
        if (getTag() != null ? !getTag().equals(tag.getTag()) : tag.getTag() != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTag() != null ? getTag().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tag{"
                + "id='" + id + "'"
                + ", tag='" + tag + "'"
                + "}";
    }

}
