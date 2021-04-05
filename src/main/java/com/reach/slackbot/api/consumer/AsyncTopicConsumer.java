package com.reach.slackbot.api.consumer;

import com.reach.slackbot.api.dto.KafkaMessage;
import com.reach.slackbot.api.service.SlackService;
import com.reach.slackbot.api.util.ToJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Slf4j
@Service
public class AsyncTopicConsumer {

    @Autowired
    SlackService slackService;

    @KafkaListener(groupId = "slackBot", topics = "${slack.bot.async.processor.topic}")
    public void asyncKafkaListener(String message) throws URISyntaxException {
        log.info("AsyncTopicConsumer::asyncKafkaListener message {}", message);
        KafkaMessage kafkaMessage = ToJSON.gson.fromJson(message, KafkaMessage.class);
        String response = slackService.sendResponseAsync(kafkaMessage);
        log.info("");
    }

}
