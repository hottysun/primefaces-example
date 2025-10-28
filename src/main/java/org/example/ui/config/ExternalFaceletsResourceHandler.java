package org.example.ui.config;

import jakarta.faces.application.Resource;
import jakarta.faces.application.ResourceHandler;
import jakarta.faces.context.FacesContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

/**
 * Custom ResourceHandler that serves Facelets from an external folder.
 */
public class ExternalFaceletsResourceHandler extends ResourceHandler {

    private static final String EXTERNAL_PATH = "C:\\Users\\sunny\\IdeaProjects\\primefaces-example\\pages";

    private final ResourceHandler wrapped;

    public ExternalFaceletsResourceHandler(ResourceHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Resource createResource(String resourceName) {
        File file = new File(EXTERNAL_PATH, resourceName);
        if (file.exists() && file.isFile()) {
            try {
                URL url = file.toURI().toURL();
                return new Resource() {
                    @Override
                    public String getRequestPath() {
                        return file.getName();
                    }

                    @Override
                    public URL getURL() {
                        return url;
                    }

                    @Override
                    public boolean userAgentNeedsUpdate(FacesContext facesContext) {
                        return false;
                    }

                    @Override
                    public InputStream getInputStream() {
                        try {
                            return new FileInputStream(file);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public Map<String, String> getResponseHeaders() {
                        return Collections.emptyMap();
                    }

                    @Override
                    public String getContentType() {
                        return "application/xhtml+xml";
                    }
                };
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // delegate to the default handler
        return wrapped.createResource(resourceName);
    }

    @Override
    public Resource createResource(String s, String s1) {
        return null;
    }

    @Override
    public Resource createResource(String s, String s1, String s2) {
        return null;
    }

    @Override
    public boolean isResourceRequest(FacesContext context) {
        return wrapped.isResourceRequest(context);
    }

    @Override
    public void handleResourceRequest(FacesContext context) throws java.io.IOException {
        wrapped.handleResourceRequest(context);
    }

    @Override
    public boolean libraryExists(String libraryName) {
        return wrapped.libraryExists(libraryName);
    }

    @Override
    public String getRendererTypeForResourceName(String resourceName) {
        return wrapped.getRendererTypeForResourceName(resourceName);
    }

}
