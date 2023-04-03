package com.spotlight.platform.helpers;

import java.io.IOException;
import java.util.Objects;

public class FixtureHelpers {
    private FixtureHelpers() { /* singleton */ }

    public static String fixture(String filename) {
        try {
            return new String(Objects.requireNonNull(FixtureHelpers.class.getResourceAsStream(filename)).readAllBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}