package com.github.cidarosa.comment.service.api.client;

import com.github.cidarosa.comment.service.api.model.ModerationInputDTO;
import com.github.cidarosa.comment.service.api.model.ModerationOutputDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface ModerationClient {

    @PostExchange("/api/moderate")
    ModerationOutputDTO moderationComment(@RequestBody ModerationInputDTO resquestDTO);
}