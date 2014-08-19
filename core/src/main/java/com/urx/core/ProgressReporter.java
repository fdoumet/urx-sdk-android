/**
 * Copyright URX 2014
 */
package com.urx.core;

/**
 * An interface for hooking into the API client's lifecycle. Can be useful for displaying progress bars or loader
 * dialogs for a more pleasant user experience under poor network conditions.
 */
public interface ProgressReporter {
    /**
     * A default {@link com.urx.core.ProgressReporter} implementation that does nothing.
     */
    static final ProgressReporter NOOP_REPORTER = new ProgressReporter() {
        @Override
        public void onStart() {}

        @Override
        public void onFinish() {}
    };

    /**
     * This should be executed by the {@link Client} implementation when the API call is first initiatied
     */
    void onStart();

    /**
     * This should be executed by the {@link Client implementation when the API call is complete, irrespective of
     * whether or not the API call was successful
     */
    void onFinish();
}
