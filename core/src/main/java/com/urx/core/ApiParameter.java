/**
 * Copyright URX 2014
 */
package com.urx.core;

/**
 * Defines an object that can be represented as an input parameter to the URX API
 */
public interface ApiParameter {
    /**
     * Constructs an API input parameter from the object
     * @return A {@link String} representation of the API input parameter
     */
    String asParameter();
}
