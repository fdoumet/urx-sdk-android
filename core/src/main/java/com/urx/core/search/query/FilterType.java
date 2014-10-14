/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * Provides a listing of all possible {@link Filter} types. These also contain the search operator prefixes for
 * constructing any kind of filter query
 */
public enum FilterType implements Value {
    Domain("domain"),
    Action("action"),
    Limit("limit"),
    Offset("offset"),
    // TODO: Remove the remaining ones for now?
    Date("date"),
    Near("near"),
    Within("within");

    protected final String repr;

    FilterType(final String repr) {
        this.repr = repr;
    }

    @Override
    public String asString() {
        return repr;
    }
}