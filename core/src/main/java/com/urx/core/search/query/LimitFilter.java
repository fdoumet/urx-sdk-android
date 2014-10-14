/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * Determines the number of results returned per query.
 */
public class LimitFilter extends PaginationFilter {
    private static final int MIN_LIMIT = 1;
    private static final int MAX_LIMIT = 10;

    LimitFilter(final int limit) {
        super(FilterType.Limit, new Number(limit, MIN_LIMIT, MAX_LIMIT));
    }
}
