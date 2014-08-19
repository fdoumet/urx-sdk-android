/**
 * Copyright URX 2014
 */
package com.urx.core;

public abstract class JsonFixtures {
    protected final byte[] articleSingle = ("{" +
        "    \"@type\": \"Article\"," +
        "    \"author\": \"Matt Fitzgerald\"," +
        "    \"headline\": \"P.J. Hairston Drafted by Miami Heat: Latest News, Reaction and Analysis\"," +
        "    \"description\": \"P.J. Hairston has endured a unique, arduous path to the highest level of pro basketball, culminating when the ex-North Carolina guard was chosen in the 1st round at No. 26\"," +
        "    \"image\": \"http://img.bleacherreport.net/img/images/photos/002/899/310/hi-res-dfd299b3caeb977538b988f50ec2ee24_crop_north.jpg\"," +
        "    \"potentialAction\": {" +
        "        \"@type\": \"ReadAction\"," +
        "        \"actionStatus\": \"PendingActionStatus\"," +
        "        \"target\": {" +
        "            \"@type\": \"EntryPoint\"," +
        "            \"urlTemplate\": \"http://urx.io/sportssite.com/miami+loses+to+chicago\"" +
        "        }" +
        "    }" +
        "}").getBytes();

    protected final byte[] articleMulti = ("{" +
        "    \"@type\": \"Article\"," +
        "    \"author\": \"Matt Fitzgerald\"," +
        "    \"headline\": \"P.J. Hairston Drafted by Miami Heat: Latest News, Reaction and Analysis\"," +
        "    \"description\": \"P.J. Hairston has endured a unique, arduous path to the highest level of pro basketball, culminating when the ex-North Carolina guard was chosen in the 1st round at No. 26\"," +
        "    \"image\": \"http://img.bleacherreport.net/img/images/photos/002/899/310/hi-res-dfd299b3caeb977538b988f50ec2ee24_crop_north.jpg\"," +
        "    \"potentialAction\": [{" +
        "        \"@type\": \"ReadAction\"," +
        "        \"actionStatus\": \"PendingActionStatus\"," +
        "        \"target\": {" +
        "            \"@type\": \"EntryPoint\"," +
        "            \"urlTemplate\": [\"http://urx.io/sportssite.com/miami+loses+to+chicago\"]" +
        "        }" +
        "    }]" +
        "}").getBytes();
}
