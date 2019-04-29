package com.swiggy.vivek.rest.dto;

public class PlaylistHeaderDto extends PlaylistDto {

    private int songCount;

    public int getSongCount() {
        return songCount;
    }

    public void setSongCount(int songCount) {
        this.songCount = songCount;
    }
}
