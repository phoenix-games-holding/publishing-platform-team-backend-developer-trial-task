package com.spotlight.platform.userprofile.api.core.profile;

import com.spotlight.platform.userprofile.api.core.exceptions.EntityNotFoundException;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserProfileServiceTest {

    private static final UserId USER_ID = UserId.valueOf("existing-user-id");
    private static final Instant LAST_UPDATE_TIMESTAMP = Instant.parse("2021-06-01T09:16:36.123Z");

    private static final UserProfile USER_PROFILE = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
            Map.of(UserProfilePropertyName.valueOf("property1"), UserProfilePropertyValue.valueOf("property1Value")));

    private final UserProfileDao userProfileDaoMock = mock(UserProfileDao.class);
    private final UserProfileService userProfileService = new UserProfileService(userProfileDaoMock);

    @Test
    void getForExistingUser_returnsUser() {
        when(userProfileDaoMock.get(any(UserId.class))).thenReturn(Optional.of(USER_PROFILE));

        assertThat(userProfileService.get(USER_ID)).usingRecursiveComparison().isEqualTo(USER_PROFILE);
    }

    @Test
    void getForNonExistingUser_throwsException() {
        when(userProfileDaoMock.get(any(UserId.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userProfileService.get(USER_ID)).isExactlyInstanceOf(EntityNotFoundException.class);
    }
}