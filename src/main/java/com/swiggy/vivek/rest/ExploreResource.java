package com.swiggy.vivek.rest;

import com.swiggy.vivek.rest.dto.ExploreDto;
import com.swiggy.vivek.rest.dto.PlaylistHeaderDto;
import com.swiggy.vivek.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("/explore")
@Produces(MediaType.APPLICATION_JSON)
public class ExploreResource {

    @Autowired
    private MusicService service;

    @GET
    @Path("/{tags}/{pageNum}")
    public Response findPlaylists(@PathParam("tags") String encodedTags,
                                                 @DefaultValue("1") @PathParam("pageNum") int pageNum)
            throws Throwable {
        String[] decodedTags = encodedTags.split("\\+");
        List<String> tags = Arrays.asList(decodedTags).stream()
                .map(value -> value.replaceAll("_", " "))
                .collect(Collectors.toList());
        ExploreDto dto = service.findPlaylists(tags, pageNum);

        return Response.ok(dto).build();
    }

}
