package com.swiggy.vivek.rest.dto;

import java.util.List;

public class ExploreDto {

    private List<PlaylistHeaderDto> playlists;
    private List<String> tags;

    public List<PlaylistHeaderDto> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistHeaderDto> playlists) {
        this.playlists = playlists;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
