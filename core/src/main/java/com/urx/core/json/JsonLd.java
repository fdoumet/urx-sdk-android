/**
 * Copyright URX 2014
 */
package com.urx.core.json;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.jayway.jsonpath.PathNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * Represents deserialized JSON-LD data. This class provides convenience methods for constructing the object from
 * raw JSON data or an in-memory map. In addition, this class also provides helper methods for JSON object traversal
 * and property access using a syntax called JSON-path. This can greatly simplify the process of accessing data from
 * the response bodies of Search API calls.
 * @see <a href="http://json-ld.org">http://json-ld.org</a>
 * @see <a href="http://www.w3.org/TR/json-ld/">http://www.w3.org/TR/json-ld/</a>
 * @see <a href="http://goessner.net/articles/JsonPath/">http://goessner.net/articles/JsonPath/</a>
 */
public class JsonLd {
    protected static final Gson GSON = new Gson();
    protected final Map<String, Object> data;

    /**
     * Constructs a {@link JsonLd} object from raw JSON data
     * @param rawData Raw JSON data
     */
    public JsonLd(final byte[] rawData) {
        this(new String(rawData));
    }

    /**
     * Constructs a {@link JsonLd} object from raw JSON data
     * @param rawData Raw JSON data
     */
    public JsonLd(final String rawData) {
        Map<String, Object> mapData = Maps.newHashMap();
        try {
            mapData = GSON.fromJson(rawData, Map.class);
        } catch (final Throwable ignored) {
            // Nothing we can do here I guess. But maybe we should rethrow?
        }
        this.data = mapData == null ? Maps.<String, Object>newHashMap() : mapData;
    }

    /**
     * Constructs a {@link JsonLd} object from an in-memory map
     * @param deserialized A map full of JSON-LD data
     */
    public JsonLd(final Map<String, Object> deserialized) {
        data = deserialized == null ? Maps.<String, Object>newHashMap() : deserialized;
    }

    /**
     * Checks for the existence of a given property given a JSON-path query
     * @param jsonPath The JSON-path query to execute
     * @return True if the property exists, false otherwise
     */
    public boolean exists(final String jsonPath) {
        return exists(JsonPath.compile(jsonPath));
    }

    /**
     * Checks for the existence of a given property given a JSON-path query object
     * @param jsonPath The JSON-path query to execute
     * @return True if the property exists, false otherwise
     */
    public boolean exists(final JsonPath jsonPath) {
        try {
            final Object thing = jsonPath.read(data);
            if (thing instanceof List) {
                return !((List) thing).isEmpty();
            } else {
                return thing != null;
            }
        } catch (final PathNotFoundException pathNotFound) {
            return false;
        }
    }

    /**
     * Retrieves a single value resulting from a JSON-path query. Note that if a list type is found, only the first
     * element of the list is returned. If nothing is found, the resulting value is a null.
     * @param jsonPath The JSON-path query to execute
     * @param <T> The type used to marshall the resulting value
     * @return A single [scalar] value if found, otherwise a null
     */
    public <T> T get(final String jsonPath) {
        return get(JsonPath.compile(jsonPath));
    }

    /**
     * Retrieves a single value resulting from a JSON-path query object. Note that if a list type is found, only the
     * first element of the list is returned. If nothing is found, the resulting value is a null.
     * @param jsonPath The JSON-path query to execute
     * @param <T> The type used to marshall the resulting value
     * @return A single [scalar] value if found, otherwise a null
     */
    public <T> T get(final JsonPath jsonPath) {
        final List<T> things = getMany(jsonPath);
        if (things == null) {
            return null;
        } else {
            return things.get(0);
        }
    }

    /**
     * Retrieves multiple values resulting from a JSON-path query. Note that if a scalar type is found, it will be
     * wrapped in a single-element list. If nothing is found, the resulting value is a null.
     * @param jsonPath The JSON-path query to execute
     * @param <T> The element type used to marshall the resulting value's element type
     * @return A list of values if found, otherwise a null
     */
    public <T> List<T> getMany(final String jsonPath) {
        return getMany(JsonPath.compile(jsonPath));
    }

    /**
     * Retrieves multiple values resulting from a JSON-path query object. Note that if a scalar type is found, it will
     * be wrapped in a single-element list. If nothing is found, the resulting value is a null.
     * @param jsonPath The JSON-path query to execute
     * @param <T> The element type used to marshall the resulting value's element type
     * @return A list of values if found, otherwise a null
     */
    public <T> List<T> getMany(final JsonPath jsonPath) {
        if (exists(jsonPath)) {
            final Object thing = jsonPath.read(data);
            if (thing instanceof List) {
                return (List<T>) thing;
            } else {
                return Lists.newArrayList((T) thing);
            }
        } else {
            return null;
        }
    }

    /**
     * Retrieves the first value found from the given JSON-path query as a {@link JsonLd} object. If nothing is found,
     * the resulting value is a null.
     * @param jsonPath The JSON-path query to execute
     * @return A {@link JsonLd} object wrapping the resulting nested value, otherwise a null
     */
    public JsonLd chroot(final String jsonPath) {
        return chroot(JsonPath.compile(jsonPath));
    }

    /**
     * Retrieves the first value found from the given JSON-path query object as a {@link JsonLd} object. If nothing is
     * found, the resulting value is a null.
     * @param jsonPath The JSON-path query to execute
     * @return A {@link JsonLd} object wrapping the resulting nested value, otherwise a null
     */
    public JsonLd chroot(final JsonPath jsonPath) {
        final Object obj = get(jsonPath);
        if (obj instanceof Map) {
            return new JsonLd((Map<String, Object>) obj);
        } else {
            return null;
        }
    }

    /**
     * Convenience method to convert this structure back into a {@link Map}. Note that this creates an immutable copy.
     * @return An immutable copy of this object's data
     */
    public Map<String, Object> toMap() {
        return ImmutableMap.copyOf(data);
    }

    @Override
    public String toString() {
        return GSON.toJson(data);
    }
}
