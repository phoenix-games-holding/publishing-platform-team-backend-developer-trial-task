package com.spotlight.platform.userprofile.api.core.profile.persistence;

import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class UserProfileDaoInMemoryTest {
    private static final UserId USER_ID = UserId.valueOf("existing-user-id");
    private static final UserId NON_EXISTING_USER_ID = UserId.valueOf("non-existing-user-id");
    private static final Instant LAST_UPDATE_TIMESTAMP = Instant.parse("2021-06-01T09:16:36.123Z");

    private static final UserProfile USER_PROFILE = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
            Map.of(UserProfilePropertyName.valueOf("property1"), UserProfilePropertyValue.valueOf("property1Value")));

    private final UserProfileDao dao = new UserProfileDaoInMemory();

    @Test
    void getNonExistingUser_OptionalEmptyReturned() {
        assertThat(dao.get(NON_EXISTING_USER_ID)).isEmpty();
    }

    @Test
    void putAndGetUser_ReturnsCorrectValues() {
        dao.put(USER_PROFILE);
        assertThat(dao.get(USER_ID)).hasValueSatisfying(
                userProfile -> assertThat(userProfile).usingRecursiveComparison().isEqualTo(USER_PROFILE));
    }
}