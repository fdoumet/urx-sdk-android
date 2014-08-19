/**
 * Copyright URX 2014
 */
package com.urx.core.json;

import com.urx.core.JsonFixtures;
import net.java.quickcheck.Generator;
import net.java.quickcheck.junit.SeedInfo;
import org.junit.Rule;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class JsonLdTest extends JsonFixtures {
    @Rule
    public SeedInfo seed = new SeedInfo();

    @Test
    public void testExists() {
        JsonLd article = new JsonLd(articleSingle);
        assertTrue(article.exists("$.potentialAction"));
        assertTrue(article.exists("$.potentialAction[*].target"));
        assertTrue(article.exists("$.potentialAction[*].target[*].urlTemplate"));
    }

    @Test
    public void testExistsMulti() {
        JsonLd article = new JsonLd(articleMulti);
        assertTrue(article.exists("$.potentialAction"));
        assertTrue(article.exists("$.potentialAction[*].target"));
        assertTrue(article.exists("$.potentialAction[*].target[*].urlTemplate"));
    }

    @Test
    public void testGet() {
        JsonLd article = new JsonLd(articleSingle);
        String urlTemplate = article.get("$.potentialAction[*].target[*].urlTemplate[*]");
        assertTrue(urlTemplate.startsWith("http://urx.io"));
    }

    @Test
    public void testGetMulti() {
        JsonLd article = new JsonLd(articleMulti);
        String urlTemplate = article.get("$.potentialAction[*].target[*].urlTemplate[*]");
        assertTrue(urlTemplate.startsWith("http://urx.io"));
    }

    @Test
    public void testRandomJsonFirstAndSecondLevels() {
        Generator<JsonLd> jsonLdGen = new JsonLdGenerator();
        for (int i = 0; i < 1000; i++) {
            JsonLd obj = jsonLdGen.next();
            for (String key : obj.data.keySet()) {
                String firstKey = "$." + key + "[*]";
                assertTrue(obj.exists(firstKey));
                Object innerObj = obj.get(firstKey);
                if (innerObj instanceof Map) {
                    for (String innerKey : ((Map<String, Object>) innerObj).keySet()) {
                        String secondKey =  innerKey + "[*]";
                        assertTrue(obj.exists(firstKey + "." + secondKey));
                        assertTrue(obj.chroot(firstKey).exists("$." + secondKey));
                    }
                }
            }
        }
    }

}
