/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * A {@link Filter} that filters by a given domain. The serialized form of this filter (per the API documentation) is
 * `domain:DOMAIN`
 */
public class DomainFilter extends Filter {
    DomainFilter(final String domain) {
        super(FilterType.Domain, new Term(domain));
    }
}
