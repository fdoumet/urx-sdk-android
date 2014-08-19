/**
 * Copyright URX 2014
 */
package com.urx.core.search;

import com.urx.core.search.query.ActionType;
import com.urx.core.search.query.QueryBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryConstructionTest extends QueryBuilder {
    @Test
    public void testActionFilter() {
        assertEquals("action:ListenAction", action(ActionType.Listen).asParameter());
    }

    @Test
    public void testAnd() {
        assertEquals("action:ListenAction AND beyonce", action(ActionType.Listen).and(term("beyonce")).asParameter());
    }

    @Test
    public void testDomainFilter() {
        assertEquals("domain:urx.com", domain("urx.com").asParameter());
    }

    @Test
    public void testNot() {
        assertEquals("NOT beyonce", not(term("beyonce")).asParameter());
    }

    @Test
    public void testOr() {
        assertEquals("domain:urx.com OR action:WatchAction", domain("urx.com").or(action(ActionType.Watch)).asParameter());
    }

    @Test
    public void testPhrase() {
        assertEquals("\"hello, world!\"", phrase("hello, world!").asParameter());
    }

    @Test
    public void testRawQuery() {
        String q = "doesnt-exist:askdhakfhkjafafh";
        assertEquals(q, raw(q).asString());
    }

    @Test
    public void testTerm() {
        String q = "lucene";
        assertEquals(q, term(q).asString());
    }
}
