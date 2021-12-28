package com.pocolifo.commons.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.pocolifo.commons.FileCommons;
import org.junit.jupiter.api.*;

class FileCommonsTest {
	static File directory;
	static File[] files;
	static int count = 5;

	static File[] createFiles(File insideDirectory) throws IOException {
		assert insideDirectory.isDirectory();
		File[] files = new File[count];

		for (int i = 0; count > i; i++) {
			File f = new File(insideDirectory, UUID.randomUUID() + ".tmp");
			f.createNewFile();
			files[i] = f;
		}

		return files;
	}

	@BeforeAll
	static void prepareFiles() throws IOException {
		files = createFiles(Constants.TEMP_DIR);
	}

	@BeforeAll
	static void prepareDirectory() throws IOException {
		File dir = new File(Constants.TEMP_DIR, UUID.randomUUID().toString());

		for (int i = 0; 3 > i; i++) {
			dir.mkdirs();

			for (int x = 0; count > i; i++) {
				File f = new File(dir, UUID.randomUUID().toString());
				f.mkdirs();

				createFiles(f);
			}
		}

		directory = dir;
	}

	@Test
	void deleteDirectoryRecursive() throws IOException {
		FileCommons.deleteDirectoryRecursive(directory);
		Assertions.assertFalse(directory.exists());
	}

	@Test
	void deleteFiles() throws IOException {
		FileCommons.deleteFiles(files);

		boolean exists = false;

		for (File file : files) {
			 exists = file.exists();
			if (exists) break;
		}

		Assertions.assertFalse(exists);
	}

	@Test
	void normalizeFileSeparators() {
		boolean works = true;

		for (String[] filePath : Constants.SAMPLE_FILE_PATHS) {
			works = Objects.equals(FileCommons.normalizeFileSeparators(filePath[0]), filePath[1]);
		}

		Assertions.assertTrue(works);
	}
}