/**
 * Copyright URX 2014
 */
package com.urx.android.resolve;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.urx.core.resolution.Resolution;

import java.util.List;

/**
 * An {@link AndroidResolver} implementation that extends the functionality of the {@link SimpleResolver} by only
 * resolving to deeplinks for apps that are current installed on the user's mobile device. If the app is not installed,
 * this resolver will default to the original web URL where the user can view the content.
 */
public class InstalledAppsResolver extends SimpleResolver {
    /**
     * Constructs a {@link SimpleResolver} from an Android app's {@link Context}
     * @param context The Android app's bounding {@link Context}
     */
    public InstalledAppsResolver(Context context) {
        super(context);
    }

    /**
     * Queries the Android {@link PackageManager} for the first app that can resolve the intended {@link Intent}.
     * @param intendedIntent The intended {@link Intent} with the emebedded deeplink
     * @return True if any app exists that can resolve the intended {@link Intent}, otherwise false
     */
    protected boolean canOpenDeeplink(final Intent intendedIntent) {
        final PackageManager packageManager = context.getPackageManager();
        final List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intendedIntent, 0);
        return resolveInfo.size() > 0;
    }

    /**
     * Extends the functionality of the {@link SimpleResolver} by only resolving to deeplinks for apps that are current
     * installed on the user's mobile device. If the app is not installed, this overrides the {@link Intent} object's
     * destination URI to the original web URL where the user can view the content.
     * @param resolution The result of calling the Resolution API
     * @return An Android {@link Intent} to be launched by the {@link AndroidResolver}
     */
    @Override
    public Intent buildIntent(Resolution resolution) {
        Intent go = super.buildIntent(resolution);
        if (resolution.deeplink != null && canOpenDeeplink(go)) {
            return go;
        } else {
            go.setData(Uri.parse(resolution.webUrl));
        }
        return go;
    }
}
