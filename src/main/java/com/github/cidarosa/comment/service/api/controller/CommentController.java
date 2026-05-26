package com.github.cidarosa.comment.service.api.controller;

import com.github.cidarosa.comment.service.api.client.ModerationClient;
import com.github.cidarosa.comment.service.api.model.CommentInputDTO;
import com.github.cidarosa.comment.service.api.model.CommentOutputDTO;
import com.github.cidarosa.comment.service.api.model.ModerationInputDTO;
import com.github.cidarosa.comment.service.api.model.ModerationOutputDTO;
import com.github.cidarosa.comment.service.domain.exceptions.ModerationRejectedException;
import com.github.cidarosa.comment.service.domain.model.Comment;
import com.github.cidarosa.comment.service.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final ModerationClient moderationClient;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentOutputDTO> search(@PageableDefault Pageable pageable) {

        Page<Comment> page = commentRepository.findAll(pageable);
        return page.map(this::convertToModel);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentOutputDTO getOne(@PathVariable UUID id) {

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        return convertToModel(comment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentOutputDTO create(@RequestBody CommentInputDTO input) {

        Comment comment = Comment.builder()
                .id(UUID.randomUUID())
                .text(input.getText())
                .author(input.getAuthor())
                .createdAt(OffsetDateTime.now())
                .build();

        ModerationInputDTO requestDTO = new ModerationInputDTO(comment.getId(), comment.getText());
        ModerationOutputDTO responseDTO = moderationClient.moderationComment(requestDTO);

        if(!responseDTO.isApproved()){
            throw  new ModerationRejectedException(responseDTO.getReason());
        }

        comment = commentRepository.saveAndFlush(comment);
        return convertToModel(comment);
    }

    private CommentOutputDTO convertToModel(Comment comment) {

        return CommentOutputDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
