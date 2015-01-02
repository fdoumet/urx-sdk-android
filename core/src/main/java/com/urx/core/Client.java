/**
 * Copyright URX 2014
 */
package com.urx.core;

import com.google.common.collect.ImmutableMap;
import com.urx.core.resolution.Resolution;
import com.urx.core.resolution.Resolve;
import com.urx.core.search.SearchResult;
import com.urx.core.search.SearchResults;
import com.urx.core.search.query.Query;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import static com.google.common.base.Throwables.propagate;

/**
 * The base class for all underlying SDK client implementations.
 * We should strive to maintain integrity in defining clear separation between the logic pre-defined here, versus the
 * platform-specific logic to be defined by concrete implementations (such as using a platform-specific transport
 * client or relying on platform-specific UI components).
 */
public abstract class Client {
    protected final ClientConfig config;

    /**
     * Constructor for SDK clients always requires a {@link ClientConfig}
     * @param config A {@link ClientConfig} for setting up the client
     */
    public Client(final ClientConfig config) {
        this.config = config;
    }

    /**
     * Encodes URL paths per <a href="https://www.ietf.org/rfc/rfc2396.txt">RFC 2396</a>
     * @param path A URL path to encode
     * @return The encoded URL path
     */
    protected static String urlEncode(final String path) {
        try {
            return URLEncoder.encode(path, "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            throw propagate(e);
        }
    }

    /**
     * Constructs an absolute URL from an {@link com.urx.core.ApiParameter} for {@link #query} or {@link #resolve}
     * @param parameter An {@link ApiParameter}
     * @return An absolute URL pointing to an API endpoint
     */
    protected String buildUrl(final ApiParameter parameter) {
        return config.apiBase + "/" + urlEncode(parameter.asParameter());
    }

    /**
     * Constructs all required API headers as determined by the {@link ClientConfig}
     * @return A map of HTTP headers for use by the underlying HTTP client
     */
    protected Map<String, String> buildApiHeaders() {
        final ImmutableMap.Builder<String, String> headersBuilder = ImmutableMap.builder();
        if (config.apiKey != null) {
            headersBuilder.put("X-API-Key", config.apiKey);
        }
        if (config.userAgent != null) {
            headersBuilder.put("User-agent", config.userAgent);
        }
        if (config.accept != null) {
            headersBuilder.put("Accept", config.accept);
        }
        return headersBuilder.build();
    }

    /**
     * Performs a search query using a well-formed {@link Query}.
     * Upon success, the {@link ResponseHandler#onSuccess} method should be invoked with the resulting
     * {@link SearchResults}, and upon failure, the {@link ResponseHandler#onFailure} method should be invoked with the
     * resulting {@link ApiException}
     * @param query A well-formed {@link Query} object
     * @param resultsHandler A callback to handle both success and failure of performing a search query
     */
    public abstract void query(final Query query, final ResponseHandler<SearchResults> resultsHandler);

    /**
     * Performs resolution to get the corresponding deeplinks for a given URI.
     * Upon success, the {@link ResponseHandler#onSuccess} method should be invoked with the resulting
     * {@link Resolution}, and upon failure, the {@link ResponseHandler#onFailure} method should be invoked with the
     * resulting {@link ApiException}. Note that a {@link SearchResult} may be used as a {@link Resolve} for convenience.
     * @param resolve A {@link Resolve} object containing the URI to resolve
     * @param resolutionHandler A callback to handle both success and failure of performing deeplink resolution
     */
    public abstract void resolve(final Resolve resolve, final ResponseHandler<Resolution> resolutionHandler);
}
