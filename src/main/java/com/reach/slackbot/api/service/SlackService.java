package com.reach.slackbot.api.service;

import com.reach.slackbot.api.dto.KafkaMessage;
import com.reach.slackbot.client.client.SlackResponseClient;
import com.reach.slackbot.client.request.SlackAsyncRequest;
import com.sun.jndi.toolkit.url.Uri;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
public class SlackService {

    @Autowired
    SlackResponseClient slackResponseClient;

    public String sendResponseAsync(KafkaMessage kafkaMessage) throws URISyntaxException {
        log.info("SlackService::sendResponseAsync URI {}", kafkaMessage.url);
        return slackResponseClient.response(
            new URI(kafkaMessage.getUrl()),
            kafkaMessage.request)
        .getBody();
    }
}
