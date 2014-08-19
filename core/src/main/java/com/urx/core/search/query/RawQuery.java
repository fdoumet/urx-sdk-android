/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * A raw {@link Query}, for expert use only. The string provided will be used as-is when querying against the Search API
 */
public class RawQuery extends Query {

    protected final String rawQuery;

    RawQuery(final String rawQuery) {
        this.rawQuery = rawQuery;
    }

    @Override
    public String asString() {
        return rawQuery;
    }
}
