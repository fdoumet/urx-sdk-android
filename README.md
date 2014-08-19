URX Android SDK
===============

This is the SDK interface for the [URX App Search API](http://a20364ad4f723e94bb34d675e89a88a5.s3-website-us-east-1.amazonaws.com/).

TODO

Installation
------------

### Gradle
```gradle
compile 'com.urx:sdk-android:0.1-SNAPSHOT'
```

### Maven
```xml
<dependency>
  <groupId>com.urx</groupId>
  <artifactId>sdk-android</artifactId>
  <version>0.1-SNAPSHOT</version>
</dependency>
```

### Building from source
```bash
mvn clean install
```

Usage
-----

### Setting up a client
```java
ClientConfig config = new ClientConfig("INSERT-API-KEY-HERE");
AndroidClient client = new AndroidClient(config);
```

### Making a search query
```java
// Build the query: "ellie goulding" AND lights AND action:ListenAction
Query q = phrase("ellie goulding").and(term("lights")).and(action(Listen));

// Execute the query asynchronously
client.query(q, new ResponseHandler<SearchResults>() {
    @Override
    public void onSuccess(SearchResults results) {
        // Handle the search results here
    }

	@Override
	public void onFailure(ApiException failure) {
		// Handle any API failures here
	}
});
```

### Resolving a deeplink
```java
// Will open the app if it is installed, otherwise will default to the mobile website
client.resolve(searchResult, installedApps(appContext));
```

See this SDK fully integrated into an [example Android app](https://github.com/URXtech/urx-sdk-android-demo).

Contributing
------------
TODO

License
-------
Copyright 2014 URX

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Documentation, Features and Examples
------------------------------------
Full details and documentation can be found on the project page here:

http://a20364ad4f723e94bb34d675e89a88a5.s3-website-us-east-1.amazonaws.com/
