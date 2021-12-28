package com.pocolifo.commons;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public class ResourceCommons {
	/**
	 * Get a resource as a File object
	 *
	 * @param clazz Class to <p>getResource</p> from
	 * @param resourcePath Path of resource to get as a File
	 * @return Resource as a File object
	 * @throws URISyntaxException Could not get resource as File
	 */
	public static File getResourceAsFile(Class<?> clazz, String resourcePath) throws URISyntaxException {
		if (!resourcePath.startsWith("/")) resourcePath = "/" + resourcePath;
		return new File(Objects.requireNonNull(clazz.getResource(resourcePath)).toURI());
	}
}
