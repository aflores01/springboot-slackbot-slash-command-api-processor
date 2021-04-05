package com.reach.slackbot.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConstantsEnum {
    AUTHORIZED("authorized"),
    UNAUTHORIZED("unauthorized"),
    COMPLETED("Command received successfully :smile:"),
    COMMAND_RECEIVED("Command received {}"),
    OPERATION_COMPLETED("Operation completed :smile:"),
    HI("Hi! "),
    USER("User: "),
    ASYNC_MESSAGE("Async message process complete!");

    public String value;
}
