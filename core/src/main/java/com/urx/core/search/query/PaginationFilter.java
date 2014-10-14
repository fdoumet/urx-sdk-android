/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * Marker class since LIMIT and OFFSET don't need to be part of a boolean expression or grouping,
 * as the rest of the query operators do.
 */
public abstract class PaginationFilter extends Filter {
    PaginationFilter(final FilterType type, final Number value) {
        super(type, value);
    }
}
