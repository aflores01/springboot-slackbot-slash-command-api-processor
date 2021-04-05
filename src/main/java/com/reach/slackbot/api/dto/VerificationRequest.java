package com.reach.slackbot.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VerificationRequest {
    public String token;
    public String challenge;
    public String type;
}
