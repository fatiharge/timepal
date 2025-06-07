package com.fatiharge.foundation.filter;

import com.fatiharge.foundation.base.CustomRuntimeException;
import io.quarkus.security.UnauthorizedException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionFilter implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

        if (exception instanceof ConstraintViolationException) {
            return Response.ok(exception.getClass( )).build( );
        }

        return Response.status(_status(exception)).entity(GlobalExceptionResponse.fromException(exception)).build( );
    }


    private Response.Status _status(Exception exception) {
        if (exception instanceof CustomRuntimeException) {

            return ((CustomRuntimeException) exception).getStatus( );

        } else if (exception instanceof WebApplicationException) {

            return ((WebApplicationException) exception).getResponse( ).getStatusInfo( ).toEnum( );

        } else if (exception instanceof UnauthorizedException) {

            return Response.Status.UNAUTHORIZED;

        }
        return Response.Status.INTERNAL_SERVER_ERROR;
    }
}

record GlobalExceptionResponse(String message, String reference, String exception) {
    public static GlobalExceptionResponse fromException(Exception exception) {
        return new GlobalExceptionResponse(
                exception.getMessage( ) == null ? exception.getClass( ).getSimpleName( ) : exception.getMessage( ),
                exception.getClass( ).getName( ),
                exception.getClass( ).getSimpleName( )
        );
    }
}
