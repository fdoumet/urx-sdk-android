/**
 * Copyright URX 2014
 */
package com.urx.core.json;

import java.util.List;
import java.util.Map;

/**
 * A wrapper for a {@link JsonLd} object that represents a schema.org Thing, providing convenience methods for
 * accessing its defined relevant properties
 * @see <a href="http://schema.org/Thing">http://schema.org/Thing</a>
 */
public class Thing extends JsonLd {
    /**
     * Constructs a {@link Thing} from a {@link JsonLd} object
     * @param jsonLd A {@link JsonLd} object
     */
    public Thing(final JsonLd jsonLd) {
        this(jsonLd.data);
    }

    /**
     * Constructs a {@link Thing} from raw JSON-LD markup
     * @param data Raw JSON-LD markup
     */
    public Thing(final String data) {
        super(data);
    }

    /**
     * Constructs a {@link Thing} from a map with JSON-LD properties
     * @param data A map with JSON-LD properties
     */
    public Thing(final Map<String, Object> data) {
        super(data);
    }

    public String getType() {
        return get("$.@type");
    }

    public String getName() {
        return get("$.name");
    }

    public boolean hasName() {
        return exists("$.name");
    }

    public String getDescription() {
        return get("$.description");
    }

    public boolean hasDescription() {
        return exists("$.description");
    }

    public String getUrl() {
        return get("$.url");
    }

    public boolean hasUrl() {
        return exists("$.url");
    }

    public String getImage() {
        return get("$.image");
    }

    public boolean hasImage() {
        return exists("$.image");
    }

    public List<String> getImages() {
        return getMany("$.image[*]");
    }

    public boolean hasImages() {
        return hasImage();
    }
}
