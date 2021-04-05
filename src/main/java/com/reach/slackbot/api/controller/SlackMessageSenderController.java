package com.reach.slackbot.api.controller;

import com.reach.slackbot.client.client.SlackMessageSenderClient;
import com.reach.slackbot.client.request.PostMessageRequest;
import com.reach.slackbot.client.response.PostMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/slack/message")
public class SlackMessageSenderController {

    @Value("${slack.app.authorization.bot.token}")
    private String token;

    @Autowired
    SlackMessageSenderClient slackMessageSenderClient;

    @PostMapping(path = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PostMessageResponse> sendMessage(@RequestBody PostMessageRequest request) {
        log.info("SlackMessageSenderController::sendMessage request {}", request.toString());
        return slackMessageSenderClient.sendMessage(token, request);
    }
}
