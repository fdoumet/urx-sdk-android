/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * A marker interface for any types that can be inputs to a {@link Query} type or to specific {@link Filter} types
 */
public interface Value {
    /**
     * Produces the serialized form of the value
     * @return The serialized form of the value, as it will look when passed into the Search API
     */
    String asString();
}
