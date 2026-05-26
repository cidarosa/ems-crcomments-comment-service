package com.github.cidarosa.comment.service.domain.exceptions;

public class ModerationRejectedException extends RuntimeException{

    public ModerationRejectedException(String message) {
        super(message);
    }
}
