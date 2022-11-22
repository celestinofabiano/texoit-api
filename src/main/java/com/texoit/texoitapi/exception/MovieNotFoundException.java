package com.texoit.texoitapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class MovieNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MovieNotFoundException(long id) {
        super("Movie ID " + id + " not found");
    }

}