package com.reach.slackbot.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayloadEnum {
    TOKEN("token"),
    TEAM_ID("team_id"),
    TEAM_DOMAIN("team_domain"),
    CHANNEL_ID("channel_id"),
    CHANNEL_NAME("channel_name"),
    USER_ID("user_id"),
    USER_NAME("user_name"),
    COMMAND("command"),
    TEXT("text"),
    API_APP_ID("api_app_id"),
    IS_ENTERPRISE_INSTALL("is_enterprise_install"),
    RESPONSE_URL("response_url"),
    TRIGGER_ID("trigger_id");

    public String value;
}
