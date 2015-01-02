/**
 * Copyright URX 2014
 */
package com.urx.core;

/**
 * Callback base class for handling API call responses
 * @param <R> Type of response coming back on success
 */
public abstract class ResponseHandler<R> {
    /**
     * Default implementation of success callback does ABSOLUTELY NOTHING!
     * @param response The response coming back from the API call on success
     */
    public void onSuccess(R response) {}

    /**
     * Default implementation of failure callback does ABSOLUTELY NOTHING!
     * @param failure The {@link ApiException} coming back from the API call on failure
     */
    public void onFailure(ApiException failure) {}
}
