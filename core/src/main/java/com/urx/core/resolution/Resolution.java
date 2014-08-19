/**
 * Copyright URX 2014
 */
package com.urx.core.resolution;

import com.urx.core.json.Thing;

/**
 * The result of calling the Resolution API
 */
public class Resolution extends Thing {
    public final String webUrl;
    public final String deeplink;

    /**
     * Constructs a {@link Resolution} from the raw response data resulting from executing against the Resolution API
     * @param data The raw response data
     */
    public Resolution(final String data) {
        super(data);
        this.webUrl = get("$.sameAs");
        this.deeplink = get("$.urlTemplate");
    }
}
