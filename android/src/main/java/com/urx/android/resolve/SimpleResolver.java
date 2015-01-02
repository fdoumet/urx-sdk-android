/**
 * Copyright URX 2014
 */
package com.urx.android.resolve;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.urx.core.resolution.Resolution;

/**
 * An {@link AndroidResolver} implementation that constructs an Android {@link Intent} using the deeplink that was
 * resolved by the preceeding Resolution API call. If no deeplink could be resolved, this resolver will default to the
 * original web URL where the user can view the content.
 *
 * It is important to note that this implementation will attempt to launch the resolved deeplink irrespective of which
 * apps are presently installed on the user's device. For this scenario, the {@link InstalledAppsResolver} may provide a
 * better user experience, as it will gracefully degrade to the web URL where the user can view the content.
 */
public class SimpleResolver extends AndroidResolver {
    /**
     * Constructs a {@link SimpleResolver} from an Android app's {@link Context}
     * @param context The Android app's bounding {@link Context}
     */
    public SimpleResolver(final Context context) {
        super(context);
    }

    /**
     * Constructs an {@link Intent} object with the deeplink that was resolved by the Resolution API call. If no
     * deeplink could be resolved, this will default to the original web URL where the user can view the content.
     * @param resolution The result of calling the Resolution API
     * @return An Android {@link Intent} to be launched by the {@link AndroidResolver}
     */
    @Override
    public Intent buildIntent(final Resolution resolution) {
        final String uri = resolution.deeplink != null ? resolution.deeplink : resolution.webUrl;
        if (uri == null)
        	return null;
        Intent go = new Intent(Intent.ACTION_VIEW);
        go.setData(Uri.parse(uri));
        return go;
    }
}
