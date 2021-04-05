package com.reach.slackbot.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CommandRequest {

    @ToString.Exclude
    public ArrayList<String> token;

    public ArrayList<String> team_Id;
    public ArrayList<String> team_domain;
    public ArrayList<String> channel_id;
    public ArrayList<String> channel_name;
    public ArrayList<String> user_id;
    public ArrayList<String> user_name;
    public ArrayList<String> command;
    public ArrayList<String> text;
    public ArrayList<String> api_app_id;
    public ArrayList<String> is_enterprise_install;
    public ArrayList<String> response_url;
    public ArrayList<String> trigger_id;
}
