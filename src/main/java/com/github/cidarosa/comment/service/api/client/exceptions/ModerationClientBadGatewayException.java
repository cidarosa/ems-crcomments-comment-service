package com.github.cidarosa.comment.service.api.client.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class ModerationClientBadGatewayException extends RuntimeException {
}
