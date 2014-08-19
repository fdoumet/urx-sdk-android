/**
 * Copyright URX 2014
 */
package com.urx.core.search;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.urx.core.json.JsonLd;
import com.urx.core.search.query.Query;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.Iterables.filter;

/**
 * A list of {@link SearchResult} objects. The {@link Iterable} interface is implemented for convenience of use (as
 * iteration is a pretty common use-case for this type of object).
 *
 * This class represents the response coming back from a query sent to the Search API.
 */
public class SearchResults extends JsonLd implements Iterable<SearchResult> {
    protected final Query query;
    protected final List<SearchResult> results;

    /**
     * Builds a {@link SearchResults} object from the original {@link Query} and a byte-array of the API response data
     * @param query The original {@link Query} used to generate this response
     * @param data A byte-array of the raw API response data
     */
    public SearchResults(final Query query, final String data) {
        super(data);
        this.query = query;
        this.results = extractResults();
    }

    /**
     * Extracts the result objects returned in this response.
     * @return A list of {@link SearchResult} objects, or an empty list if there were none
     */
    protected List<SearchResult> extractResults() {
        final List<Map<String, Object>> results = getMany("$.result");
        final List<Map<String, Object>> nonNullResults = Lists.newArrayList(filter(results, notNull()));
        if (!nonNullResults.isEmpty()) {
            return Lists.transform(nonNullResults, new Function<Map<String, Object>, SearchResult>() {
                @Override
                public SearchResult apply(final Map<String, Object> resultData) {
                    return new SearchResult(resultData);
                }
            });
        } else {
            return Lists.newArrayList();
        }
    }

    /**
     * Accessor for the result objects. Note that this method purposefully re-extracts the {@link SearchResult}
     * objects in order to maintain immutability.
     * @return A list of {@link SearchResult} objects
     */
    public List<SearchResult> getResults() {
        return extractResults();
    }

    /**
     * Accessor for the original query object used to generate this list of results
     * @return The original {@link Query} object
     */
    public Query getQuery() {
        return query;
    }

    /**
     * Gets the number of search results returned from the API
     * @return The number of search results
     */
    public int size() {
        return results.size();
    }

    @Override
    public Iterator<SearchResult> iterator() {
        return results.iterator();
    }
}
