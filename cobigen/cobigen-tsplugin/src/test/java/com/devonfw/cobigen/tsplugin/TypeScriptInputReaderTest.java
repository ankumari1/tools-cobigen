package com.devonfw.cobigen.tsplugin;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import com.devonfw.cobigen.api.constants.ExternalProcessConstants;
import com.devonfw.cobigen.impl.externalprocess.ExternalProcessHandler;
import com.devonfw.cobigen.tsplugin.inputreader.TypeScriptInputReader;
import com.devonfw.cobigen.tsplugin.merger.constants.Constants;

/**
 *
 */
public class TypeScriptInputReaderTest {

    /** Test resources root path */
    private static String testFileRootPath = "src/test/resources/testdata/unittest/inputreader/";

    /** Initializing connection with server */
    private static ExternalProcessHandler request = ExternalProcessHandler
        .getExternalProcessHandler(ExternalProcessConstants.HOST_NAME, ExternalProcessConstants.PORT);

    /**
     * Starts the server and initializes the connection to it
     */
    @BeforeClass
    public static void initializeServer() {
        assertEquals(true, request.executingExe(Constants.EXE_NAME, TypeScriptMergerTest.class));
        assertEquals(true, request.initializeConnection());
    }

    @Test
    public void testValidTypeScriptFile() {

        try {
            File baseFile = new File(testFileRootPath + "baseFile.ts");

            new TypeScriptInputReader().isValidInput(baseFile);
        }

        finally {

        }

    }

}
