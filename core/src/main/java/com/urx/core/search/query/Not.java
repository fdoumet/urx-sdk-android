/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * A logical negation of the given {@link Query} clause: `NOT QUERY`
 */
public class Not extends Query {
    protected final Query query;

    Not(final Query query) {
        this.query = query;
    }

    @Override
    public String asString() {
        // TODO: I believe the query clause actually needs to be grouped in order to preserve the full
        // semantics of a logical NOT w/r/t the Search API query syntax
        return "NOT " + query.asString();
    }
}
