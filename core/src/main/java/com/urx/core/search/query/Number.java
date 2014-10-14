package com.urx.core.search.query;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

/**
 * Holds a bounded value for Pagination queries.
 */
public class Number implements Value {
    protected final Integer num;

    public Number(final Integer num, final Integer min, final Integer max) {
        this.num = checkNotNull(num);
        if (num < min) {
            throw new IllegalArgumentException(format("Error, invalid value %s < %s", num, min));
        }
        if (num > max) {
            throw new IllegalArgumentException(format("Error, invalid value %s > %s", num, max));
        }
    }

    @Override
    public String asString() {
        return num.toString();
    }
}
