package com.swiggy.vivek.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Component
@Path("/status")
public class StatusResource {

    @GET
    public Response status() {
        return Response.ok("Status Fine").build();
    }

}
