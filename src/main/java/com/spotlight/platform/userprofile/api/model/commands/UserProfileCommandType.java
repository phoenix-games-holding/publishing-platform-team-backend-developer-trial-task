package com.spotlight.platform.userprofile.api.model.commands;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserProfileCommandType {
    INCREMENT(Constants.INCREMENT_VALUE), REPLACE(Constants.REPLACE_VALUE), COLLECT(Constants.COLLECT_VALUE);

    public static class Constants {
        public static final String INCREMENT_VALUE = "increment";
        public static final String REPLACE_VALUE = "replace";
        public static final String COLLECT_VALUE = "collect";
    }

    private final String value;

    UserProfileCommandType(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }
}