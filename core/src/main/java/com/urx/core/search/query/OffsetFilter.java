/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

import com.google.common.base.Preconditions;

/**
 * Determines which page of results to return.
 */
public class OffsetFilter extends PaginationFilter {
    private static final int MIN_OFFSET = 0;
    private static final int MAX_OFFSET = 10;

    OffsetFilter(final int offset) {
        super(FilterType.Offset, new Number(offset, MIN_OFFSET, MAX_OFFSET));
    }
}
