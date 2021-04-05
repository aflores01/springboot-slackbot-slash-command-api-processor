package com.reach.slackbot.api.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface ToJSON {
    Gson gson = new Gson();
    GsonBuilder gsonBuilder = gson.newBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

    public default String toJson() {
        return new GsonBuilder().create().toJson(this);
    }
}
