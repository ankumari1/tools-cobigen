package com.devonfw.cobigen.tsplugin.inputreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devonfw.cobigen.api.constants.ExternalProcessConstants;
import com.devonfw.cobigen.api.exception.InputReaderException;
import com.devonfw.cobigen.api.extension.InputReader;
import com.devonfw.cobigen.api.to.FileTo;
import com.devonfw.cobigen.impl.exceptions.ConnectionExceptionHandler;
import com.devonfw.cobigen.impl.externalprocess.ExternalProcessHandler;
import com.devonfw.cobigen.tsplugin.merger.constants.Constants;

/**
 *
 */
public class TypeScriptInputReader implements InputReader {

    /** Logger instance. */
    private static final Logger LOG = LoggerFactory.getLogger(TypeScriptInputReader.class);

    /**
     * Instance that handles all the operations performed to the external server, like initializing the
     * connection and sending new requests
     */
    private ExternalProcessHandler request = ExternalProcessHandler
        .getExternalProcessHandler(ExternalProcessConstants.HOST_NAME, ExternalProcessConstants.PORT);

    /**
     * Exception handler related to connectivity to the server
     */
    private ConnectionExceptionHandler connectionExc = new ConnectionExceptionHandler();

    /**
     * Creates a new {@link TypeScriptInputReader}
     */
    public TypeScriptInputReader() {

        try {
            // We first check if the server is already running
            request.startConnection();
            if (request.isNotConnected()) {
                startServerConnection();
            }
        } catch (IOException e) {
            // If it is not currently running, we need to execute it
            LOG.info("Server is not currently running. Let's initialize it");
            startServerConnection();
        }

    }

    /**
     * Deploys the server and tries to initialize a new connection between CobiGen and the server
     */
    private void startServerConnection() {
        request.executingExe(Constants.EXE_NAME, this.getClass());
        request.initializeConnection();
    }

    @Override
    public boolean isValidInput(Object input) {

        String basecontents = null;
        File file = new File(input.toString());

        if (request.isNotConnected()) {
            startServerConnection();
        }
        try {
            basecontents = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {

        }

        FileTo fileTo = new FileTo(basecontents);
        HttpURLConnection conn = request.getConnection("POST", "Content-Type", "application/json", "isValidInput");

        StringBuffer importsAndExports = new StringBuffer();
        StringBuffer body = new StringBuffer();

        if (request.sendRequest(fileTo, conn, "UTF-8")) {

            try (InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(isr);) {

            }

            catch (Exception e) {

                connectionExc.handle(e);
            }
        }
        return false;

    }

    @Override
    public Map<String, Object> createModel(Object input) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Object> getInputObjects(Object input, Charset inputCharset) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> getTemplateMethods(Object input) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Object> getInputObjectsRecursively(Object input, Charset inputCharset) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object read(Path path, Charset inputCharset, Object... additionalArguments) throws InputReaderException {
        // TODO Auto-generated method stub
        return null;
    }

}
