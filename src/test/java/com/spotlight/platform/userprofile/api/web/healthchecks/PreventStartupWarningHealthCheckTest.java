package com.spotlight.platform.userprofile.api.web.healthchecks;

import com.codahale.metrics.health.HealthCheck;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PreventStartupWarningHealthCheckTest {

    private static final PreventStartupWarningHealthCheck HEALTH_CHECK = new PreventStartupWarningHealthCheck();

    @Test
    void healthcheckCalled_returnsHealthy() {
        assertThat(HEALTH_CHECK.check()).isEqualTo(HealthCheck.Result.healthy());
    }
}