/**
 * Copyright URX 2014
 */
package com.urx.core;

/**
 * Represents a failure that results from an API call. Typically, this is represented by an HTTP status code greater
 * than 3xx
 */
public class ApiException extends RuntimeException {

    protected final int httpStatus;

    /**
     * Constructs an {@link ApiException} from a status code and error message
     * @param httpStatus The HTTP status code coming back from the API call
     * @param message A useful error message describing the nature of the API failure
     */
    public ApiException(final int httpStatus, final String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    /**
     * Constructs an {@link ApiException} from a status code, an error message, and wrapping an original
     * {@link Throwable}. Typically this constructor should be prefered to provide as much extra context as possible in
     * order to better handle the failure and provide an informative user experience
     * @param httpStatus The HTTP status code coming back from the API call
     * @param message A useful error message describing the nature of the API failure
     * @param t The wrapped {@link Throwable}
     */
    public ApiException(final int httpStatus, final String message, final Throwable t) {
        super(message, t);
        this.httpStatus = httpStatus;
    }

    /**
     * Accessor for the HTTP status code returned by the failing API call
     * @return The failure's HTTP status code
     */
    public int getHttpStatus() {
        return httpStatus;
    }
}
