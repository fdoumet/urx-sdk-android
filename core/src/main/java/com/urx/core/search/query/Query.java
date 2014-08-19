/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

import com.urx.core.ApiParameter;

/**
 * Base class for all types of query constructions. It also implements the {@link ApiParameter} interface so that it
 * can be directly provided as input to the Search API without further mutations.
 */
public abstract class Query implements ApiParameter {

    /**
     * This represents the logical AND search operator
     * @param anotherQuery Another {@link Query} object
     * @return An {@link And} object representing the intersection of the current query and anotherQuery
     */
    public And and(final Query anotherQuery) {
        return new And(this, anotherQuery);
    }

    /**
     * This represents the logical OR search operator
     * @param anotherQuery Another {@link Query} object
     * @return An {@link And} object representing the union of the current query and anotherQuery
     */
    public Or or(final Query anotherQuery) {
        return new Or(this, anotherQuery);
    }

    /**
     * This method must be implemented by all {@link Query} subtypes in order to ensure that it can be handled by the
     * Search API
     * @return The query-string representation of the object
     */
    public abstract String asString();

    /**
     * Mixes in {@link ApiParameter} behavior
     * @return The {@link Query} object serialized as a Search API input parameter
     */
    @Override
    public String asParameter() {
        return asString();
    }

    @Override
    public String toString() {
        return asString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Query && toString().equals(obj.toString());
    }
}
