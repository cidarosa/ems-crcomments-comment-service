package com.github.cidarosa.comment.service.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ModerationInputDTO {

    private UUID commentId; //UUID
    private String text;
}
