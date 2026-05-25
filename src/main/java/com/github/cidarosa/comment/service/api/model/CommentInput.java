package com.github.cidarosa.comment.service.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CommentInput {

    private UUID text;
    private String author;
}
