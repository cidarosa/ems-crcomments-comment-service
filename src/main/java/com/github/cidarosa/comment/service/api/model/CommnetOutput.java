package com.github.cidarosa.comment.service.api.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder

public class CommnetOutput {

    private UUID id;
    private String text;
    private String author;
    private OffsetDateTime createdAt;
}
