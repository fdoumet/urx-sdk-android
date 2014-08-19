/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Represents a logical grouping of multiple {@link Query} clauses. In serialized form, this resembles:
 * `(CLAUSE_1 CLAUSE_2 ... CLAUSE_N)`
 */
public class Group extends Query {
    protected static final Joiner JOINER = Joiner.on(' ');
    protected final List<Query> queries;

    Group(final Query query) {
        if (query instanceof Group) {
            this.queries = ImmutableList.copyOf(((Group) query).queries);
        } else {
            this.queries = ImmutableList.of(query);
        }
    }

    Group(final List<Query> queries) {
        this.queries = queries;
    }

    @Override
    public String asString() {
        return "(" + JOINER.join(queries) + ')';
    }
}
