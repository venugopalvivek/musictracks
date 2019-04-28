package com.swiggy.vivek.services;

import com.swiggy.vivek.persistence.dao.TagDao;
import com.swiggy.vivek.persistence.model.Tag;
import com.swiggy.vivek.rest.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicService {

    @Autowired
    private TagDao tagDao;

    public void createTag(String tag) {
        tagDao.createTag(tag);
    }

    public List<TagDto> findTags(String prefix) {
        List<Tag> tagEntities = tagDao.findTags(prefix);
        return tagEntities.stream().map(tag -> toDto(tag)).collect(Collectors.toList());
    }

    private TagDto toDto(Tag tag) {
        TagDto dto = new TagDto();
        dto.setTag(tag.getTag());
        return dto;
    }

}
