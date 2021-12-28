package com.pocolifo.commons;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

import org.jetbrains.annotations.NotNull;

public class HttpCommons {
	/**
	 * Get a URL with the exception
	 *
	 * @param url The URL
	 * @return The actual URL
	 */
	public static URL getUrl(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(url + " is not a URL");
		}
	}

	/**
	 * Read a URL to a byte array
	 *
	 * @param url URL to visit
	 * @return Byte array of the contents
	 * @throws IOException Trouble while reading the URL
	 */
	public static byte[] download(@NotNull String url) throws IOException {
		return download(getUrl(url));
	}

	/**
	 * Read a URL to a byte array
	 *
	 * @param url URL to download from
	 * @return Byte array of the contents
	 * @throws IOException Trouble while reading the URL
	 */
	public static byte[] download(@NotNull URL url) throws IOException {
		return StreamCommons.readInputStream(url.openStream());
	}

	/**
	 * Download and save a file
	 *
	 * @param url URL to download from
	 * @param destinationFile The file to write to
	 * @throws IOException Could not download content from URL
	 */
	public static void downloadFile(@NotNull String url, File destinationFile) throws IOException {
		Files.write(destinationFile.toPath(), download(url));
	}

	/**
	 * Download and save a file
	 *
	 * @param url URL to download from
	 * @param destinationFile The file to write to
	 * @throws IOException Could not download content from URL
	 */
	public static void downloadFile(@NotNull URL url, File destinationFile) throws IOException {
		Files.write(destinationFile.toPath(), download(url));
	}
}
