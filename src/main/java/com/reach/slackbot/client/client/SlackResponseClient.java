package com.reach.slackbot.client.client;

import com.reach.slackbot.client.request.SlackAsyncRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

@FeignClient(name = "slack-async-client", url = "https://", path = "/")
public interface SlackResponseClient {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> response(URI uri, @RequestBody SlackAsyncRequest request);

}