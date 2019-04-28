package com.swiggy.vivek.rest;

import com.swiggy.vivek.rest.dto.TagDto;
import com.swiggy.vivek.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/tags")
public class TagResource {

    @Autowired
    private MusicService service;

    @POST
    public Response upsertTag(TagDto tag) {
        service.createTag(tag.getTag());
        return Response.ok().build();
    }

    @GET
    public Response findTags(@QueryParam("prefix") String prefix) {
        List<TagDto> tags = service.findTags(prefix);
        return Response.ok(tags).build();
    }

}
