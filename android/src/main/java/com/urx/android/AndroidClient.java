/**
 * Copyright URX 2014
 */
package com.urx.android;

import android.os.Build;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.urx.core.*;
import com.urx.core.resolution.Resolution;
import com.urx.core.resolution.Resolve;
import com.urx.core.search.SearchResult;
import com.urx.core.search.SearchResults;
import com.urx.core.search.query.Query;

import org.apache.http.Header;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Android {@link Client} implementation, providing the HTTP client transport layer (using {@link AsyncHttpClient})
 */
public class AndroidClient extends Client {
    protected final AsyncHttpClient client;
    protected final SyncHttpClient syncedClient;
    protected final Cache<String, String> responseCache;
    protected ProgressReporter queryProgressReporter = ProgressReporter.NOOP_REPORTER;
    protected ProgressReporter resolveProgressReporter = ProgressReporter.NOOP_REPORTER;

    /**
     * Constructs an {@link AndroidClient} from a {@link ClientConfig}
     * @param config The {@link ClientConfig} to configure the client
     */
    public AndroidClient(final ClientConfig config) {
        super(config);
        // TODO: Maybe this can be put somewhere else?
        config.userAgent = "urx-client/0.1 (android; " + Build.VERSION.RELEASE + ")";
        client = new AsyncHttpClient();
        client.setUserAgent(config.userAgent);
        client.setEnableRedirects(false);
        syncedClient = new SyncHttpClient();
        syncedClient.setUserAgent(config.userAgent);
        syncedClient.setEnableRedirects(false);
        // TODO: Should we expose these settings to be tweaked?
        responseCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .maximumSize(100)
            .build();
    }

    /**
     * Performs a search query using a well-formed {@link Query}.
     * Upon success, the {@link ResponseHandler#onSuccess} method is invoked with the resulting {@link SearchResults},
     * and upon failure, the {@link ResponseHandler#onFailure} method is invoked with the resulting
     * {@link ApiException}. Note that queries are cached in this implementation for network performance reasons.
     * @param query A well-formed {@link Query} object
     * @param resultsHandler A callback to handle both success and failure of performing a search query
     */
    @Override
    public void query(final Query query, final ResponseHandler<SearchResults> resultsHandler) {
        cachedGet(buildUrl(query), new ResponseHandler<String>() {
            @Override
            public void onSuccess(String response) {
                resultsHandler.onSuccess(new SearchResults(query, response));
            }

            @Override
            public void onFailure(ApiException failure) {
                resultsHandler.onFailure(failure);
            }
            
            @Override
            public void onCancel(){
            	resultsHandler.onCancel();
            }
        }, queryProgressReporter, false);
    }
    
    /**
     * Performs a search query using a well-formed {@link Query}.
     * Upon success, the {@link ResponseHandler#onSuccess} method is invoked with the resulting {@link SearchResults},
     * and upon failure, the {@link ResponseHandler#onFailure} method is invoked with the resulting
     * {@link ApiException}. Note that queries are cached in this implementation for network performance reasons.
     * @param query A well-formed {@link Query} object
     * @param resultsHandler A callback to handle both success and failure of performing a search query
     */
    @Override
    public void querySync(final Query query, final ResponseHandler<SearchResults> resultsHandler) {
        cachedGet(buildUrl(query), new ResponseHandler<String>() {
            @Override
            public void onSuccess(String response) {
                resultsHandler.onSuccess(new SearchResults(query, response));
            }

            @Override
            public void onFailure(ApiException failure) {
                resultsHandler.onFailure(failure);
            }
            
            @Override
            public void onCancel(){
            	resultsHandler.onCancel();
            }
        }, queryProgressReporter, true);
    }

    /**
     * Performs resolution to get the corresponding deeplinks for a given URI.
     * Upon success, the {@link ResponseHandler#onSuccess} method is invoked with the resulting {@link Resolution}, and
     * upon failure, the {@link ResponseHandler#onFailure} method is invoked with the resulting {@link ApiException}.
     * Note that a {@link SearchResult} may be used as a {@link Resolve} for convenience. Also note that resolves are
     * cached in this implementation for network performance reasons.
     * @param resolve A {@link Resolve} object containing the URI to resolve
     * @param resolutionHandler A callback to handle both success and failure of performing deeplink resolution
     */
    @Override
    public void resolve(final Resolve resolve, final ResponseHandler<Resolution> resolutionHandler) {
        cachedGet(buildUrl(resolve), new ResponseHandler<String>() {
            @Override
            public void onSuccess(String response) {
                resolutionHandler.onSuccess(new Resolution(response));
            }

            @Override
            public void onFailure(ApiException failure) {
                resolutionHandler.onFailure(failure);
            }
            
            @Override
            public void onCancel(){
            	resolutionHandler.onCancel();
            }
        }, resolveProgressReporter, false);
    }
    
    /**
     * Performs resolution to get the corresponding deeplinks for a given URI.
     * Upon success, the {@link ResponseHandler#onSuccess} method is invoked with the resulting {@link Resolution}, and
     * upon failure, the {@link ResponseHandler#onFailure} method is invoked with the resulting {@link ApiException}.
     * Note that a {@link SearchResult} may be used as a {@link Resolve} for convenience. Also note that resolves are
     * cached in this implementation for network performance reasons.
     * @param resolve A {@link Resolve} object containing the URI to resolve
     * @param resolutionHandler A callback to handle both success and failure of performing deeplink resolution
     */
    @Override
    public void resolveSync(final Resolve resolve, final ResponseHandler<Resolution> resolutionHandler) {
        cachedGet(buildUrl(resolve), new ResponseHandler<String>() {
            @Override
            public void onSuccess(String response) {
                resolutionHandler.onSuccess(new Resolution(response));
            }

            @Override
            public void onFailure(ApiException failure) {
                resolutionHandler.onFailure(failure);
            }
            
            @Override
            public void onCancel(){
            	resolutionHandler.onCancel();
            }
        }, resolveProgressReporter, true);
    }

    /**
     * Resets the HTTP headers on the client
     * @param headers A map of HTTP headers to be set on the outgoing request
     * @param client The client that needs its headers reset
     */
    protected void resetHeaders(final AsyncHttpClient client, final Map<String, String> headers) {
        client.removeAllHeaders();
        for (final Map.Entry<String, String> entry : headers.entrySet()) {
            client.addHeader(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Wraps API calls with the built-in response cache to optimize for network performance. If the request has already
     * been successfully made, the cached response is returned. Otherwise, the API call is made and cached only if
     * a successful response comes back.
     * @param url The API endpoint to invoke
     * @param responseHandler A callback object for handling success/failure
     * @param progressReporter The callback object to report the progress of the current API call
     * @param sync Whether the call should be synchronized
     */
    protected void cachedGet(final String url, final ResponseHandler<String> responseHandler,
                             final ProgressReporter progressReporter, final boolean sync) {
        final String cachedResponse = responseCache.getIfPresent(url);
        if (cachedResponse != null) {
            responseHandler.onSuccess(cachedResponse);
        } else {
        	ResponseHandler<String> rh = new ResponseHandler<String>() {
                @Override
                public void onSuccess(String response) {
                    responseCache.put(url, response);
                    responseHandler.onSuccess(response);
                }

                @Override
                public void onFailure(ApiException failure) {
                    responseHandler.onFailure(failure);
                }
                
                @Override
                public void onCancel(){
                	responseHandler.onCancel();
                }
            };
            
            if (!sync)
            	get(url, rh, progressReporter);
            else
            	synchedGet(url, rh, progressReporter);
        }
    }

    /**
     * Executes the underlying GET request against an API endpoint. The relevant callbacks are invoked for handling the
     * response success or failure, in addition to reporting the progress updates of the execution as necessary.
     * @param url The API endpoint to invoke
     * @param responseHandler A callback object for handling success/failure
     * @param progressReporter The callback object to report the progress of the current API call
     */
    protected void get(final String url, final ResponseHandler<String> responseHandler,
            final ProgressReporter progressReporter)
    {
    	get(client, url, responseHandler, progressReporter);
    }
    
    /**
     * Executes the underlying GET request against an API endpoint. The relevant callbacks are invoked for handling the
     * response success or failure, in addition to reporting the progress updates of the execution as necessary.
     * @param url The API endpoint to invoke
     * @param responseHandler A callback object for handling success/failure
     * @param progressReporter The callback object to report the progress of the current API call
     */
    protected void synchedGet(final String url, final ResponseHandler<String> responseHandler,
            final ProgressReporter progressReporter)
    {
    	get(syncedClient, url, responseHandler, progressReporter);
    }
    
    private void get(final AsyncHttpClient client, final String url, final ResponseHandler<String> responseHandler,
                       final ProgressReporter progressReporter) {
        resetHeaders(client, buildApiHeaders());
        progressReporter.onStart();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressReporter.onFinish();
                // TODO: Why does the async client treat 300+ as errors?
                final String responseString = new String(responseBody != null ? responseBody : new byte[0]);
                responseHandler.onFailure(
                    new ApiException(statusCode, responseString, new RuntimeException("Unknown error response")));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                progressReporter.onFinish();
                final String responseString = new String(responseBody != null ? responseBody : new byte[0]);
                if (statusCode > 303 || responseString.length() == 0) {
                    responseHandler.onFailure(new ApiException(statusCode, responseString, error));
                } else {
                    responseHandler.onSuccess(responseString);
                }
            }
            
            @Override
            public void onCancel()
            {
            	 progressReporter.onFinish();
            	 responseHandler.onCancel();
            }
        });
    }

    /**
     * Sets the progress reporter for Search API calls
     * @param queryProgressReporter The new callback object for Search API call progress updates
     */
    public void setQueryProgressReporter(final ProgressReporter queryProgressReporter) {
        this.queryProgressReporter = queryProgressReporter;
    }

    /**
     * Sets the progress reporter for Resolution API calls
     * @param resolveProgressReporter The new callback object for Resolution API call progress updates
     */
    public void setResolveProgressReporter(final ProgressReporter resolveProgressReporter) {
        this.resolveProgressReporter = resolveProgressReporter;
    }
    
    /**
     * Overrides the threadpool implementation used when queuing/pooling requests. By default,
     * Executors.newCachedThreadPool() is used.
     *
     * @param threadPool an instance of {@link ExecutorService} to use for queuing/pooling
     *                   requests.
     */
    public AndroidClient setThreadPool(final ExecutorService threadPool) {
    	if (client != null)
    		client.setThreadPool(threadPool);
    	return this;
    }
}
