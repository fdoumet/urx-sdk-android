/**
 * Copyright URX 2014
 */
package com.urx.core.json;

import net.java.quickcheck.Generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static net.java.quickcheck.generator.CombinedGenerators.*;
import static net.java.quickcheck.generator.PrimitiveGenerators.integers;
import static net.java.quickcheck.generator.PrimitiveGenerators.letterStrings;

public class JsonLdGenerator implements Generator<JsonLd> {

    protected Generator<Set<String>> keyGen = sets(letterStrings(1, 6), 1, 10);
    protected Generator<Object> valueGen = new ValueGenerator();

    @Override
    public JsonLd next() {
        try {
            Map<String, Object> map = new HashMap<>();
            for (String key : keyGen.next()) {
                map.put(key, valueGen.next());
            }
            return new JsonLd(map);
        } catch (StackOverflowError oops) {
            return next();
        }
    }

    static class ValueGenerator implements Generator {
        protected Generator valueGen =
            oneOf(maps(letterStrings(1, 6), this, integers(1, 2)))
                .add(lists(this, integers(1, 10)))
                .add(integers())
                .add(letterStrings());

        @Override
        public Object next() {
            return valueGen.next();
        }
    }
}
