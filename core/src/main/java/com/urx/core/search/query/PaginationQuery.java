package com.urx.core.search.query;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Given a query, add LIMIT or OFFSET operators. For example,
 *
 *      Query q = QueryBuilder.phrase("bluegrass music")
 *      PaginationQuery pq = paged(q).limit(10).offset(3)
 */
public class PaginationQuery extends Query {
    private OffsetFilter offset;
    private LimitFilter limit;
    private Query query;

    private PaginationQuery(final Query query) {
        this.query = query;
    }

    public static PaginationQuery paged(final Query query) {
        return new PaginationQuery(checkNotNull(query));
    }

    public PaginationQuery limit(int limit) {
        this.limit = new LimitFilter(limit);
        return this;
    }

    public PaginationQuery offset(int offset) {
        this.offset = new OffsetFilter(offset);
        return this;
    }

    @Override
    public String asString() {
        StringBuilder sb = new StringBuilder(query.asString());
        if (limit != null) {
            sb.append(" ").append(limit.asString());
        }
        if (offset != null) {
            sb.append(" ").append(offset.asString());
        }
        return sb.toString();
    }
}