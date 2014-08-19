/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * A term-matching {@link Query}, for individual term(s): `TERM` or `TERM_1 TERM_2`
 */
public class Term extends Query implements Value {
    protected final String text;

    Term(final String text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return text;
    }
}
