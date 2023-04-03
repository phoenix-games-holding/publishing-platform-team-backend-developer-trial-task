package com.spotlight.platform.userprofile.api.model.profile.primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserProfilePropertyValueTest {
    private static final String STRING_VALUE = "someString";
    private static final int INTEGER_VALUE = 5;
    private static final List<String> LIST_VALUE = List.of("one", "two");

    @Test
    void equals_ReturnsTrueForEqualValues() {
        assertThat(UserProfilePropertyValue.valueOf(STRING_VALUE)).isEqualTo(UserProfilePropertyValue.valueOf(STRING_VALUE));
        assertThat(UserProfilePropertyValue.valueOf(INTEGER_VALUE)).isEqualTo(UserProfilePropertyValue.valueOf(INTEGER_VALUE));
        assertThat(UserProfilePropertyValue.valueOf(LIST_VALUE)).isEqualTo(UserProfilePropertyValue.valueOf(LIST_VALUE));
    }

    /*
    @Test
    void serialization_WorksCorrectly() {
        JsonTestUtils.assertSerializedObjectIsEqualToFile(Map.of("string", STRING_VALUE, "integer", INTEGER_VALUE, "list", LIST_VALUE),
                "fixtures/com/spotlight/platform/commons/model/userProfilePropertyValueMap.json");
    }

    @Test
    void deserialization_WorksCorrectly() {
        JsonHelpers.mapLoader("fixtures/com/spotlight/platform/commons/model/userProfilePropertyValueMap.json")
                .loadSerializeAndDeserialize();
    }
*/
}