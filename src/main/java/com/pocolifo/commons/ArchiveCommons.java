package com.pocolifo.commons;

import java.awt.SystemColor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;
import java.util.function.Function;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArchiveCommons {
	/**
	 * Extracts a zip file
	 *
	 * @param inputStream ZipInputStream for the zip file
	 * @param toDirectory Directory to extract files to
	 * @param naming Rename files that are being extracted
	 * @param pathChecking If true, the entire path of a file must match the <p>extractOnly</p> argument. If false, the
	 *                     file name will be matched.
	 * @param extractOnly Extract certain files only. See <p>pathChecking</p>.
	 * @throws IOException Could not extract zip
	 */
	public static void extractZip(@NotNull ZipInputStream inputStream, @NotNull File toDirectory,
								  @NotNull Function<String, String> naming, boolean pathChecking,
								  @Nullable String... extractOnly) throws IOException {
		for (ZipEntry entry; (entry = inputStream.getNextEntry()) != null;) {
			boolean extractingFile = false;

			if (extractOnly == null || 1 > extractOnly.length) {
				extractingFile = true;
			} else {
				for (String file : extractOnly) {
					file = Objects.requireNonNull(file);

					extractingFile = (pathChecking && file.equals(entry.getName())) ||
							(!pathChecking && file.equals(FileCommons.getFileName(entry.getName())));
					break;
				}
			}

			if (extractingFile) {
				File writeOutFile = new File(toDirectory,
					naming.apply(FileCommons.normalizeFileSeparators(entry.getName())));

				boolean valid = writeOutFile.getCanonicalPath()
					.startsWith(toDirectory.getCanonicalPath() + File.separator);

				// protect against zip slip
				if (!valid) {
					throw new SecurityException("(zip slip detected) Entry outside of target directory: "
							+ entry.getName());
				}

				if (entry.isDirectory()) {
					Files.createDirectories(writeOutFile.toPath());
				} else {
					FileCommons.createDirectoriesForFile(writeOutFile);
					Files.write(writeOutFile.toPath(), StreamCommons.readInputStream(inputStream));
				}
			}
		}
	}

	/**
	 * Extracts a zip file
	 *
	 * @param zipFile Target zip file
	 * @param toDirectory Directory to extract files to
	 * @param naming Rename files that are being extracted
	 * @param pathChecking If true, the entire path of a file must match the <p>extractOnly</p> argument. If false, the
	 *                     file name will be matched.
	 * @param extractOnly Extract certain files only. See <p>pathChecking</p>.
	 * @throws IOException Could not extract zip
	 */
	public static void extractZip(@NotNull File zipFile, @NotNull File toDirectory,
								  @NotNull Function<String, String> naming, boolean pathChecking,
								  @Nullable String... extractOnly) throws IOException {
		assert zipFile.isFile();

		if (!toDirectory.exists()) Files.createDirectories(toDirectory.toPath());
		assert toDirectory.exists();

		try (ZipInputStream inputStream = new ZipInputStream(new FileInputStream(zipFile))) {
			extractZip(inputStream, toDirectory, naming, pathChecking, extractOnly);
		}
	}

	/**
	 * Extracts a zip file
	 *
	 * @param zipFile Zip file to extract
	 * @param toDirectory Directory to extract files to
	 * @param pathChecking If true, the entire path of a file must match the <p>extractOnly</p> argument. If false, the
	 *                     file name will be matched.
	 * @param extractOnly Extract certain files only. See <p>pathChecking</p>.
	 * @throws IOException Could not extract zip
	 */
	public static void extractZip(@NotNull File zipFile, @NotNull File toDirectory, boolean pathChecking,
								  @Nullable String... extractOnly)
			throws IOException {
		extractZip(zipFile, toDirectory, (s) -> s, pathChecking, extractOnly);
	}

	/**
	 * Extracts all files inside a zip archive
	 *
	 * @param zipFile Zip file to extract
	 * @param toDirectory Directory to extract to
	 * @throws IOException Could not extract zip
	 */
	public static void extractZip(@NotNull File zipFile, @NotNull File toDirectory) throws IOException {
		extractZip(zipFile, toDirectory, false);
	}

	/**
	 * Extracts all files inside a zip archive to the directory that the zip file is located in
	 *
	 * @param zipFile Zip file to extract
	 * @throws IOException Could not extract the archive
	 */
	public static void extractZip(@NotNull File zipFile) throws IOException {
		assert zipFile.getParentFile().isDirectory();
		extractZip(zipFile, zipFile.getParentFile());
	}
}
