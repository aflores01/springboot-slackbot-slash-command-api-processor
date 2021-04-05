package com.reach.slackbot.client.client;

import com.reach.slackbot.client.request.PostMessageRequest;
import com.reach.slackbot.client.response.PostMessageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "slack-message-sender-client", url = "https://slack.com")
public interface SlackMessageSenderClient {

    @PostMapping(
        path = "/api/chat.postMessage",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<PostMessageResponse> sendMessage(
        @RequestHeader String authorization,
        @RequestBody PostMessageRequest request
    );
}
