package com.swiggy.vivek.rest;

import com.swiggy.vivek.exceptions.MusicTracksException;
import com.swiggy.vivek.rest.dto.PlaylistDetailsDto;
import com.swiggy.vivek.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/playlists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlaylistResource {

    @Autowired
    private MusicService service;

    @POST
    public Response createPlaylist(PlaylistDetailsDto playlistDto) throws Throwable {
        String id = service.createPlaylist(playlistDto);
        return Response.status(Response.Status.CREATED).entity(id).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePlaylist(@PathParam("id") String id) throws Throwable {
        service.deletePlaylist(id);
        return Response.ok().build();
    }

    @PUT
    public Response updatePlaylist(@PathParam("id") String id) throws Throwable {
        return null;
    }

    @GET
    @Path("/{id}")
    public Response getPlaylist(@PathParam("id") String id) throws Throwable {
        PlaylistDetailsDto dto = service.getPlaylist(id);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

}
