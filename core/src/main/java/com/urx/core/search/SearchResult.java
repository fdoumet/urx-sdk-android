/**
 * Copyright URX 2014
 */
package com.urx.core.search;

import com.urx.core.json.Thing;
import com.urx.core.resolution.Resolve;

import java.util.Map;

/**
 * Represents an individual search result coming back from the Search API. As the API conforms quite tightly to
 * schema.org and JSON-LD, this object is a direct extension of the base {@link Thing} class
 */
public class SearchResult extends Thing implements Resolve {

    /**
     * Constructs a {@link SearchResult} object from a raw map of data
     * @param data
     */
    public SearchResult(final Map<String, Object> data) {
        super(data);
    }

    /**
     * Extracts the `potentialAction` property from the search result. This potential action can be used furthermore to
     * resolve a deeplink/web URL, display a call-to-action, or obtain the name of the destination app/service
     * @return A {@link Thing} containing the `potentialAction` of the search result
     */
    public Thing getPotentialAction() {
        return new Thing(chroot("$.potentialAction[*]"));
    }

    /**
     * Allows the {@link SearchResult} object to be used as a parameter against the Resolution API.
     * Note that unfortunately for now, we will need to keep the ugly replacement string
     * @return The `potentialAction` target URL template
     */
    @Override
    public String asParameter() {
        final String uri = getPotentialAction().get("$.target[*].urlTemplate[*]");
        if (uri != null) {
            // TODO: Ewwwww
            return uri.replaceAll("//", "/").replace("https:/urx.io/", "");
        } else {
            return "";
        }
    }
}
