package com.github.cidarosa.comment.service.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CommentOutputDTO {

    private UUID id;
    private String text;
    private String author;
    private OffsetDateTime createdAt;
}
