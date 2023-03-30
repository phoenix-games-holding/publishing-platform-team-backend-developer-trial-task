package com.spotlight.platform.userprofile.api.core.json;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class JsonMapperTest {

    public static final String FIXTURE_PATH = "/fixtures/core/json/testEntityWithInstantsIsoString.json";

    @Test
    void serializeInstants_CorrectlySerialized() throws Exception {
        var serializedEntity = JsonMapper.getInstance().writeValueAsString(new TestEntityWithInstants());

        assertThat(serializedEntity).isEqualToIgnoringWhitespace(getFixture());
    }

    @Test
    void deserializeInstants_CorrectlyDeserialized() throws Exception {
        var deserializedEntity = JsonMapper.getInstance().readValue(getFixture(), TestEntityWithInstants.class);

        assertThat(deserializedEntity).usingRecursiveComparison().isEqualTo(new TestEntityWithInstants());
    }

    private String getFixture() throws IOException {
        return new String(Objects.requireNonNull(getClass().getResourceAsStream(FIXTURE_PATH)).readAllBytes());
    }
}