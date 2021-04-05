package com.reach.slackbot.api.controller;

import com.reach.slackbot.api.dto.CommandRequest;
import com.reach.slackbot.api.dto.KafkaMessage;
import com.reach.slackbot.api.dto.VerificationRequest;
import com.reach.slackbot.api.dto.VerificationResponse;
import com.reach.slackbot.api.enums.ConstantsEnum;
import com.reach.slackbot.api.enums.PayloadEnum;
import com.reach.slackbot.api.producer.AsyncTopicProducer;
import com.reach.slackbot.api.service.SlackService;
import com.reach.slackbot.client.request.SlackAsyncRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/slack")
public class SlackController {

    @Autowired
    SlackService slackService;

    @Autowired
    AsyncTopicProducer producer;

    @PostMapping(path = "/events", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VerificationResponse> slackEventVerification(@RequestBody VerificationRequest request) {
        log.info(request.toString());
        return new ResponseEntity<VerificationResponse>(new VerificationResponse(request.challenge), HttpStatus.OK);
    }

    @PostMapping(path = "/events/get", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> getInfo(@RequestParam Map<CommandRequest, String> request) {
        log.info(ConstantsEnum.COMMAND_RECEIVED.value, request);
        return new ResponseEntity<String>(ConstantsEnum.OPERATION_COMPLETED.value, HttpStatus.OK);
    }

    @PostMapping(path = "/events/hi", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> sayHi(@RequestParam Map<CommandRequest, String> request) {
        log.info(ConstantsEnum.COMMAND_RECEIVED.value, request);
        String username = request.get("user_name");
        String response = new StringBuilder(ConstantsEnum.HI.value)
            .append(username)
            .toString();
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/events/security_access", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> securityAccess(@RequestParam Map<CommandRequest, String> request) {
        log.info(ConstantsEnum.COMMAND_RECEIVED.value, request);
        String username = request.get(PayloadEnum.USER_NAME.value);
        String password = request.get(PayloadEnum.TEXT.value);
        String authorization;
        authorization = (password.equals("${slack.command.password}") && username.equals("${slack.command.username}")) ?
            ConstantsEnum.AUTHORIZED.value : ConstantsEnum.UNAUTHORIZED.value;
        String response = new StringBuilder(ConstantsEnum.USER.value)
            .append(username)
            .append(" ")
            .append(authorization)
            .toString();
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/events/async", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity asyncResponse(@RequestParam Map<CommandRequest, String> request) throws URISyntaxException {
        log.info("SlackController::asyncResponse request {}", request.toString());

        KafkaMessage message = new KafkaMessage();
        message.setUrl(request.get(PayloadEnum.RESPONSE_URL.value));
        message.setRequest(new SlackAsyncRequest(
            ConstantsEnum.ASYNC_MESSAGE.value
        ));
        producer.sendAsyncResponseToKafkaTopic(message);

        log.info("SlackController::asyncResponse async command processed");
        return new ResponseEntity(HttpStatus.OK);
    }
}
