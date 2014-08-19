/**
 * Copyright URX 2014
 */
package com.urx.android.progress;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import com.urx.core.ProgressReporter;

/**
 * A {@link ProgressReporter} implementation that triggers an Android-native {@link Dialog} to be visible when the API
 * call is first made, and then disappear once the response comes back. Useful for display a loading message while
 * waiting for search results or URI resolution.
 */
public class DialogProgressReporter implements ProgressReporter {
    protected final Dialog dialog;

    /**
     * Constructs a {@link DialogProgressReporter} from an Android app's {@link Context}. This builds a
     * {@link ProgressDialog} object with the text "Loading content", by default
     * @param context The Android app's bounding {@link Context}
     */
    public DialogProgressReporter(final Context context) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Loading content");
        progressDialog.setIndeterminate(true);
        dialog = progressDialog;
    }

    /**
     * Constructs a {@link DialogProgressReporter} from a native {@link Dialog} object.
     * @param dialog A native {@link Dialog} object to toggle during API callbacks
     */
    public DialogProgressReporter(final Dialog dialog) {
        this.dialog = dialog;
    }

    /**
     * Invoked when the API call is first initiatied
     */
    @Override
    public void onStart() {
        dialog.show();
    }

    /**
     * Invoked when the API call is complete, irrespective of whether or not the API call was successful
     */
    @Override
    public void onFinish() {
        dialog.hide();
    }
}
