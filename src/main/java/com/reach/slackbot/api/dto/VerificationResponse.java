package com.reach.slackbot.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VerificationResponse {
    public String challenge;
}
