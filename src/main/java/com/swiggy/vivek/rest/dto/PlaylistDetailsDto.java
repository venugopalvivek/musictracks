package com.swiggy.vivek.rest.dto;

import java.util.List;

public class PlaylistDetailsDto extends PlaylistDto {

    private List<String> songIds;

    public List<String> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<String> songIds) {
        this.songIds = songIds;
    }
}
