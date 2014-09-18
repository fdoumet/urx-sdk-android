/**
 * Copyright URX 2014
 */
package com.urx.android.resolve;

import android.content.Context;
import android.content.Intent;
import com.urx.core.ResponseHandler;
import com.urx.core.resolution.Resolution;

/**
 * Base {@link ResponseHandler} implementation specific to the Resolution API for use on Android devices
 */
public abstract class AndroidResolver extends ResponseHandler<Resolution> {

    protected final Context context;

    /**
     * Constructs an {@link AndroidResolver} from an Android app's {@link Context}
     * @param context The Android app's bounding {@link Context}
     */
    AndroidResolver(final Context context) {
        this.context = context;
    }

    /**
     * Static helper for constructing a {@link SimpleResolver} from an Android app's {@link Context}
     * @param context The Android app's bounding {@link Context}
     */
    public static AndroidResolver simple(final Context context) {
        return new SimpleResolver(context);
    }

    /**
     * Static helper for constructing an {@link InstalledAppsResolver} from an Android app's {@link Context}
     * @param context The Android app's bounding {@link Context}
     */
    public static AndroidResolver installedApps(final Context context) {
        return new InstalledAppsResolver(context);
    }

    /**
     * When invoked, constructs an Android {@link Intent} to be launched on successful URI resolution
     * @param resolution The result of calling the Resolution API
     * @return An Android {@link Intent} to be launched
     */
    abstract Intent buildIntent(final Resolution resolution);

    /**
     * On successful URI resolution, this method will be invoked to build an Android {@link Intent} object from the
     * result {@link Resolution}, and then launch the {@link Intent} from the current app {@link Context}.
     * @param resolution The result of calling the Resolution API
     */
    @Override
    public void onSuccess(final Resolution resolution) {
    	Intent intent = buildIntent(resolution);
    	if (intent != null)
    		context.startActivity(intent);
    }
}
