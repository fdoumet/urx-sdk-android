/**
 * Copyright URX 2014
 */
package com.urx.core.resolution;

/**
 * An input to the Resolution API containing only a single URI against which to resolve potential deeplinks
 */
public class UriResolve implements Resolve {
    protected final String uri;

    /**
     * Constructs a Resolution API input value from a URI
     * @param uri The URI to resolve
     */
    public UriResolve(final String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return asParameter();
    }

    @Override
    public String asParameter() {
        return uri;
    }
}
