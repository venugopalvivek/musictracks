package com.swiggy.vivek.rest.exceptionmappers;

import com.swiggy.vivek.exceptions.MusicTracksException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MusicTracksExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        int statusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        ExceptionResponse exceptionResponse = null;
        if (exception instanceof MusicTracksException) {
            statusCode = ((MusicTracksException) exception).getCode();
            exceptionResponse = toMusicTracksExceptionResponse((MusicTracksException) exception);
        } else {
            exceptionResponse = toGenericExceptionResponse(exception);
        }

        return Response.status(statusCode).entity(exceptionResponse).build();
    }

    private ExceptionResponse toMusicTracksExceptionResponse(MusicTracksException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.code = exception.getCode();
        exceptionResponse.message = exception.getMessage();
        if (exception.getCause() != null)
            exceptionResponse.cause = exception.getCause().getMessage();
        return exceptionResponse;
    }

    private ExceptionResponse toGenericExceptionResponse(Throwable exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.message = exception.getMessage();
        if (exception.getCause() != null)
            exceptionResponse.cause = exception.getCause().getMessage();

        return exceptionResponse;
    }
}
