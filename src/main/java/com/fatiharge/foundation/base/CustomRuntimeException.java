package com.fatiharge.foundation.base;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public abstract class CustomRuntimeException extends RuntimeException {

    private final Response.Status status;

    public CustomRuntimeException(
            String message,
            Response.Status status
    ) {
        super(message);
        this.status = status;
    }

    public CustomRuntimeException(String message) {
        super(message);
        this.status = Response.Status.BAD_REQUEST;
    }

    public CustomRuntimeException() {
        super();
        this.status = Response.Status.BAD_REQUEST;
    }
}
