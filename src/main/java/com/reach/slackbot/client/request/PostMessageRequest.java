package com.reach.slackbot.client.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostMessageRequest {
    public String channel;
    public String text;
}
