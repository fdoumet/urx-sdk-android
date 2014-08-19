/**
 * Copyright URX 2014
 */
package com.urx.core;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Configuration data object
 */
public class ClientConfig {
    /**
     * Your URX API key
     */
    public final String apiKey;
    /**
     * Base URL for constructing API calls
     */
    public String apiBase = "https://beta.urx.io";
    /**
     * User agent string to transmit to the API
     */
    public String userAgent = null;
    /**
     * Contents of the "Accept" HTTP header
     */
    public String accept = "application/json";

    /**
     * Constructs a configuration data object with the API key preset
     * @param apiKey Your URX API key
     */
    public ClientConfig(final String apiKey) {
        checkNotNull(apiKey, "Your API key must not be NULL");
        this.apiKey = apiKey;
    }
}
