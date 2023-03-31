package com.spotlight.platform.userprofile.api.model.profile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

public record UserProfile(UserId userId, Instant latestUpdateTime,
                          Map<UserProfilePropertyName, UserProfilePropertyValue> userProfileProperties) {
    @JsonCreator
    public UserProfile(UserId userId, Instant latestUpdateTime,
            Map<UserProfilePropertyName, UserProfilePropertyValue> userProfileProperties) {
        this.userId = userId;
        this.latestUpdateTime = latestUpdateTime;
        this.userProfileProperties = Collections.unmodifiableMap(userProfileProperties);
    }
}
