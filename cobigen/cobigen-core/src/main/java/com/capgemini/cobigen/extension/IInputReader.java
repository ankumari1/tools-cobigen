/*
 * Copyright © Capgemini 2013. All rights reserved.
 */
package com.capgemini.cobigen.extension;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * This is an extension point to enable further generator input support. Implementations should inherit this
 * interface and should be registered via an implemented {@link ITriggerInterpreter} to be integrated into the
 * CobiGen generation process.
 * @author mbrunnli (09.10.2013)
 */
public interface IInputReader {

    /**
     * This function will be called if matching triggers or matching templates should be retrieved for a given
     * input object
     * @param input
     *            object to be checked
     * @return <code>true</code> if the given input can be processed by the implementing {@link IInputReader},
     *         <code>false</code> otherwise
     * @author mbrunnli (08.04.2014)
     */
    public boolean isValidInput(Object input);

    /**
     * This function should create the FreeMarker object model from the given input
     * @param input
     *            object the model should be build of (not null)
     * @return a key to object {@link Map} representing an object model for the generation
     * @author mbrunnli (09.10.2013)
     */
    public Map<String, Object> createModel(Object input);

    /**
     * States whether the given input object combines multiple input objects to be used for generation
     * @param input
     *            to be checked
     * @return <code>true</code> if the given input combines multiple input objects for generation<br>
     *         <code>false</code>, otherwise
     * @author mbrunnli (03.06.2014)
     */
    public boolean combinesMultipleInputObjects(Object input);

    /**
     * Will return the set of combined input objects if the given input combines multiple input objects (resp.
     * {@link #combinesMultipleInputObjects(Object)} returns <code>true</code>).
     * @param input
     *            the combined input object
     * @param inputCharset
     *            to be used for reading new inputs
     * @return a list of input objects, the generation should be triggered for each.
     * @author mbrunnli (03.06.2014)
     */
    public List<Object> getInputObjects(Object input, Charset inputCharset);

}