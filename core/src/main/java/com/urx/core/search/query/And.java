/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * The logical intersection of two {@link Query} clauses: `left AND right`
 */
public class And extends Query {
    protected final Query left;
    protected final Query right;

    And(final Query left, final Query right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String asString() {
        // TODO: I believe both left and right clauses actually need to be grouped in order to preserve the full
        // semantics of a logical intersection w/r/t the Search API query syntax
        return left.asString() + " AND " + right.asString();
    }
}
