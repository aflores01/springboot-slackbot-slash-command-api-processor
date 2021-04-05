package com.reach.slackbot.api.producer;

import com.reach.slackbot.api.dto.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncTopicProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${slack.bot.async.processor.topic}")
    String TOPIC;

    public void sendAsyncResponseToKafkaTopic(KafkaMessage message) {
        log.info("AsyncTopicProducer::sendAsyncResponseToKafkaTopic message {}", message.toJson());
        kafkaTemplate.send(TOPIC, message.toJson());
        log.info("AsyncTopicProducer::sendAsyncResponseToKafkaTopic SENT to topic {}", TOPIC);
    }
}
