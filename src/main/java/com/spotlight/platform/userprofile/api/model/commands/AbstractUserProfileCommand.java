package com.spotlight.platform.userprofile.api.model.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

// See for more details: https://github.com/FasterXML/jackson-docs/wiki/JacksonPolymorphicDeserialization
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property =
        AbstractUserProfileCommand.Properties.TYPE, visible = true)
@JsonSubTypes({ //
        @JsonSubTypes.Type(value = IncrementPropertiesUserProfileCommand.class, name = UserProfileCommandType.Constants.INCREMENT_VALUE), //
})
public class AbstractUserProfileCommand {

    public static class Properties {
        public static final String USER_ID = "userId";
        public static final String TYPE = "type";
        public static final String PROPERTIES = "properties";
    }

    @NotNull
    @JsonProperty(Properties.TYPE)
    private final UserProfileCommandType type;

    @Valid
    @NotNull
    @JsonProperty(Properties.USER_ID)
    private final UserId userId;

    @NotEmpty
    @JsonProperty(Properties.PROPERTIES)
    private final Map<String, Object> properties;

    public AbstractUserProfileCommand(UserProfileCommandType type, UserId userId, Map<String, Object> properties) {
        this.type = type;
        this.userId = userId;
        this.properties = properties;
    }
}
