/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

import static com.google.common.collect.Lists.newArrayList;

/**
 * A builder class for constructing arbitrarily-complex search queries, whilst providing a level of type-safety to
 * ensure [mostly] sane queries. This class should stay in lock-step with the API search operators.
 */
public abstract class QueryBuilder {

    /**
     * Constructs a hand-crafted, "unsafe", query
     * @param rawQuery A raw query string to be used as-is
     * @return A {@link RawQuery} wrapping the input query string
     */
    public static RawQuery raw(final String rawQuery) {
        return new RawQuery(rawQuery);
    }

    /**
     * Constructs a term query from a term
     * @param term A term string to search for
     * @return A {@link Term} wrapping the input term
     */
    public static Term term(final String term) {
        return new Term(term);
    }

    /**
     * Constructs a phrase object from a phrase
     * @param phrase A complete phrase to match verbatim
     * @return A {@link Phrase} wrapping the input phrase
     */
    public static Phrase phrase(final String phrase) {
        // TODO: Escape quotation marks?
        return new Phrase(phrase);
    }

    /**
     * Constructs a filter query limited by a given domain
     * @param domain A well-formed domain to filter by
     * @return A {@link DomainFilter} to filter by the given domain
     */
    public static DomainFilter domain(final String domain) {
        // TODO: Domain check?
        return new DomainFilter(domain);
    }

    /**
     * Constucts a filter query limited by a given {@link ActionType}
     * @param action The {@link ActionType} to filter by
     * @return An {@link ActionFilter} to filter by the given {@link ActionType}
     */
    public static ActionFilter action(final ActionType action) {
        return new ActionFilter(action);
    }

    /**
     * Negates the given {@link Query}. This is analogous to the logical NOT operator
     * @param queryClause The {@link Query} to negate
     * @return The logical inverse of the provided {@link Query}
     */
    public static Not not(final Query queryClause) {
        return new Not(queryClause);
    }

    /**
     * Groups one or more {@link Query} clauses as a single unit
     * @param queries One or more {@link Query} clauses to group
     * @return A {@link Group}
     */
    public static Group group(final Query... queries) {
        return new Group(newArrayList(queries));
    }
}
