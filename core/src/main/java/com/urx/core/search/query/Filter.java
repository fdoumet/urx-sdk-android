/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * Base class for all {@link Filter} types, providing a default {@link Query#asString()} implementation of the form:
 * `FILTER_TYPE:VALUE`
 */
public abstract class Filter extends Query {
    protected final FilterType type;
    protected final Value value;

    Filter(final FilterType type, final Value value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String asString() {
        return type.asString() + ":" + value.asString();
    }
}
