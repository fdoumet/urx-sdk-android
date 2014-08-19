/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * A {@link Filter} that filters by a given {@link ActionType}. The serialized form of this filter (per the API
 * documentation) is `action:ACTION_TYPE`
 */
public class ActionFilter extends Filter {
    ActionFilter(final ActionType actionType) {
        super(FilterType.Action, actionType);
    }
}
