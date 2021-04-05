package com.reach.slackbot.api.dto;

import com.reach.slackbot.api.util.ToJSON;
import com.reach.slackbot.client.request.SlackAsyncRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class KafkaMessage implements ToJSON {

    public String url;
    public SlackAsyncRequest request;
}
