package com.spotlight.platform.userprofile.api.web;

import com.spotlight.platform.userprofile.api.core.json.JsonMapper;
import com.spotlight.platform.userprofile.api.model.configuration.UserProfileApiConfiguration;
import com.spotlight.platform.userprofile.api.web.healthchecks.PreventStartupWarningHealthCheck;
import com.spotlight.platform.userprofile.api.web.modules.JsonModule;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class UserProfileApiApplication extends Application<UserProfileApiConfiguration> {
    @Override
    public String getName() {
        return UserProfileApiConfiguration.APPLICATION_NAME;
    }

    @Override
    public void initialize(Bootstrap<UserProfileApiConfiguration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.setObjectMapper(JsonMapper.getInstance());
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .modules(new JsonModule())
                .printDiagnosticInfo()
                .build());
    }

    @Override
    public void run(UserProfileApiConfiguration configuration, Environment environment) {
        registerHealthChecks(environment);
    }

    public static void main(String[] args) throws Exception {
        new UserProfileApiApplication().run(args);
    }

    private void registerHealthChecks(Environment environment) {
        environment.healthChecks().register(PreventStartupWarningHealthCheck.NAME, new PreventStartupWarningHealthCheck());
    }
}
