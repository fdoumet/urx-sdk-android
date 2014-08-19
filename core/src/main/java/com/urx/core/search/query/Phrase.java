/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * A phrase-matching {@link Query}, for more exact searches: `"PHRASE"`
 */
public class Phrase extends Query implements Value {
    protected final String phrase;

    Phrase(final String phrase) {
        this.phrase = phrase;
    }

    @Override
    public String asString() {
        return "\"" + phrase + "\"";
    }
}
