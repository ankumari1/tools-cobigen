package com.capgemini.cobigen.eclipse.generator;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import com.capgemini.cobigen.CobiGen;
import com.capgemini.cobigen.config.ContextConfiguration.ContextSetting;
import com.capgemini.cobigen.eclipse.common.constants.ConfigResources;
import com.capgemini.cobigen.eclipse.common.exceptions.GeneratorProjectNotExistentException;
import com.capgemini.cobigen.exceptions.InvalidConfigurationException;

/**
 *
 * @author mbrunnli (02.12.2014)
 */
public abstract class AbstractCobiGenWrapper {

    /**
     * Target Project for the generation
     */
    private IProject targetProject;

    /**
     * Referenz to native {@link CobiGen} API
     */
    protected CobiGen cobiGen;

    /**
     * Creates a new generator instance
     *
     * @throws GeneratorProjectNotExistentException
     *             if the generator configuration project "RF-Generation" is not existent
     * @throws CoreException
     *             if the generator configuration project could not be opened
     * @throws IOException
     *             if the generator project could not be found or read
     * @throws InvalidConfigurationException
     *             if the context configuration is not valid
     * @author mbrunnli (21.03.2014)
     */
    public AbstractCobiGenWrapper() throws GeneratorProjectNotExistentException, CoreException,
        InvalidConfigurationException, IOException {
        cobiGen = initializeGenerator();
    }

    /**
     * Initializes the {@link CobiGen} with the correct configuration
     *
     * @return the configured{@link CobiGen}
     * @throws CoreException
     *             if an internal eclipse exception occurs
     * @throws GeneratorProjectNotExistentException
     *             if the generator configuration folder does not exist
     * @throws IOException
     *             if the generator project could not be found or read
     * @throws InvalidConfigurationException
     *             if the context configuration is not valid
     * @author mbrunnli (05.02.2013)
     */
    private CobiGen initializeGenerator() throws GeneratorProjectNotExistentException, CoreException,
        InvalidConfigurationException, IOException {

        IProject generatorProj = ConfigResources.getGeneratorConfigurationProject();
        return new CobiGen(generatorProj.getLocationURI());
    }

    /**
     * Sets the generation root target for all templates
     *
     * @param proj
     *            {@link IProject} which represents the target root
     * @author mbrunnli (13.02.2013)
     */
    public void setGenerationTargetProject(IProject proj) {

        targetProject = proj;
        cobiGen.setContextSetting(ContextSetting.GenerationTargetRootPath, proj.getProject().getLocation()
            .toString());
    }

    /**
     * Returns the generation target project
     *
     * @return the generation target project
     * @author mbrunnli (13.02.2013)
     */
    public IProject getGenerationTargetProject() {

        return targetProject;
    }
}
