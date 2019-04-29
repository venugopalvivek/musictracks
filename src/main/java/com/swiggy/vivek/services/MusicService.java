package com.swiggy.vivek.services;

import com.swiggy.vivek.exceptions.MusicTracksException;
import com.swiggy.vivek.persistence.dao.PlaylistDao;
import com.swiggy.vivek.persistence.dao.TagDao;
import com.swiggy.vivek.persistence.model.PlaylistEntity;
import com.swiggy.vivek.persistence.model.TagEntity;
import com.swiggy.vivek.rest.dto.ExploreDto;
import com.swiggy.vivek.rest.dto.PlaylistDetailsDto;
import com.swiggy.vivek.rest.dto.PlaylistDto;
import com.swiggy.vivek.rest.dto.PlaylistHeaderDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicService {

    private static final int PAGE_SIZE = 15;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private PlaylistDao playlistDao;

    // only for testing
    public void createTag(String tag) {
        tagDao.createTag(tag);
    }

    public List<String> findTags(String prefix) throws MusicTracksException {
        if (StringUtils.isBlank(prefix)) {
            throw new MusicTracksException(Response.Status.BAD_REQUEST.getStatusCode(),
                    "Starts with text is invalid");
        }
        List<TagEntity> tagEntities = tagDao.findTags(prefix);
        return tagEntities.stream().map(tagEntity -> tagEntity.getTag()).collect(Collectors.toList());
    }

    public String createPlaylist(PlaylistDetailsDto request) {
        List<String> tags = request.getTags();
        tagDao.createTags(tags);

        PlaylistEntity playlistEntity = playlistDao.createPlaylist(toEntity(request));
        return playlistEntity.getId().toString();
    }

    public void deletePlaylist(String id) throws MusicTracksException {
        try {
            playlistDao.deletePlaylist(id);
        } catch (IllegalArgumentException ex) {
            // Mostly because not found
            throw new MusicTracksException(Response.Status.NOT_FOUND.getStatusCode(),
                    String.format("Playlist %s not found", id), ex);
        }
    }

    public PlaylistDetailsDto getPlaylist(String id) throws MusicTracksException {
        try {
            PlaylistEntity entity = playlistDao.find(id);
            return toPlaylistDetails(entity);
        } catch (IllegalArgumentException ex) {
            // Mostly because not found
            throw new MusicTracksException(Response.Status.NOT_FOUND.getStatusCode(),
                    String.format("Playlist %s not found", id), ex);
        }
    }

    public ExploreDto findPlaylists(List<String> tags, int pageNum) throws MusicTracksException {
        if (CollectionUtils.isEmpty(tags)) {
            throw new MusicTracksException(Response.Status.BAD_REQUEST.getStatusCode(),
                    "Cannot search with empty tags");
        }

        long docCount = playlistDao.searchCount(tags);

        ExploreDto dto = new ExploreDto();
        dto.setTotal(docCount);

        if (pageNum * PAGE_SIZE - docCount > PAGE_SIZE) {
            throw new MusicTracksException(Response.Status.BAD_REQUEST.getStatusCode(),
                    "Page number is out of bounds");
        }

        if (docCount > 0) {
            List<PlaylistEntity> entities = playlistDao.search(tags, PAGE_SIZE, pageNum);
            List<PlaylistHeaderDto> dtos = entities.stream()
                    .map(entity -> toPlaylistHeader(entity))
                    .collect(Collectors.toList());

            List<String> entityTags = entities.stream()
                    .flatMap(entity -> entity.getTags().stream())
                    .distinct()
                    .collect(Collectors.toList());

            dto.setPlaylists(dtos);
            dto.setTags(entityTags);
        }

        return dto;
    }

    private PlaylistDetailsDto toPlaylistDetails(PlaylistEntity entity) {
        PlaylistDetailsDto dto = new PlaylistDetailsDto();
        setValues(entity, dto);
        dto.setSongIds(entity.getSongIds());
        return dto;
    }

    private PlaylistHeaderDto toPlaylistHeader(PlaylistEntity entity) {
        PlaylistHeaderDto dto = new PlaylistHeaderDto();
        setValues(entity, dto);
        dto.setSongCount(entity.getSongIds().size());
        return dto;
    }

    private void setValues(PlaylistEntity entity, PlaylistDto dto) {
        dto.setId(entity.getId().toString());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setTags(entity.getTags());
        dto.setLikes(entity.getLikes());
        dto.setPlayCount(entity.getPlayCount());
    }

    private PlaylistEntity toEntity(PlaylistDetailsDto dto) {
        PlaylistEntity entity = new PlaylistEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setSongIds(dto.getSongIds());
        entity.setTags(dto.getTags());
        return entity;
    }

}
