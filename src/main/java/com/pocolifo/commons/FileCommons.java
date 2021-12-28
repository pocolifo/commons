package com.pocolifo.commons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.jetbrains.annotations.NotNull;

public class FileCommons {
	/**
	 * Default separator character, regardless of OS
	 */
	public static final String SEPARATOR = "/";

	public static final String[] ALL_SEPARATORS = {
			"\\\\",
			"/"
	};

	/**
	 * Delete a directory and all files inside it
	 *
	 * @param directory Target directory
	 * @throws IOException Could not delete directory or file not found
	 */
	public static void deleteDirectoryRecursive(@NotNull File directory) throws IOException {
		assert directory.isDirectory();

		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				deleteDirectoryRecursive(file);
			} else {
				deleteFiles(file);
			}
		}

		directory.delete();
	}

	/**
	 * Delete multiple files
	 *
	 * @param files Files to delete
	 * @throws IOException File could not be deleted
	 */
	public static void deleteFiles(@NotNull File... files) throws IOException {
		for (File file : files) {
			Files.deleteIfExists(file.toPath());
		}
	}

	/**
	 * Change all file separators to the universal separator (default is "/")
	 *
	 * @param filePath File path to normalize
	 * @return Normalized string
	 */
	public static String normalizeFileSeparators(@NotNull String filePath) {
		for (String separator : ALL_SEPARATORS) {
			filePath = filePath.replaceAll(separator, SEPARATOR);
		}

		return filePath;
	}

	/**
	 * Convert universal separator to OS specific separator
	 *
	 * @param filePath File path to convert
	 * @return Converted string
	 */
	public static String osSpecificFileSeparators(@NotNull String filePath) {
		return filePath.replaceAll(SEPARATOR, File.separator);
	}

	/**
	 * Get the name of the file inside a string
	 *
	 * @param filePath The file path string
	 * @return The file name
	 */
	public static String getFileName(@NotNull String filePath) {
		filePath = normalizeFileSeparators(filePath);
		if (!filePath.contains(SEPARATOR)) return filePath;

		return filePath.substring(filePath.lastIndexOf(SEPARATOR) + 1);
	}

	/**
	 * Creates parent directories for a file if necessary
	 *
	 * @param file File to create directories for
	 * @throws IOException Could not create directories
	 */
	public static void createDirectoriesForFile(@NotNull File file) throws IOException {
		if (!file.getParentFile().isDirectory()) Files.createDirectories(file.getParentFile().toPath());
	}
}
