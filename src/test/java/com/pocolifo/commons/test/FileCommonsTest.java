package com.pocolifo.commons.test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.pocolifo.commons.FileCommons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(2)
class FileCommonsTest {
	@Test
	void deleteDirectoryRecursive() throws IOException {
		File deleting = new File(Constants.TEMP_DIR, "extract-1");
		FileCommons.deleteDirectoryRecursive(deleting);
		Assertions.assertFalse(deleting.exists());
	}

	@Test
	void deleteFiles() throws IOException {
		File[] files = new File[] {
				new File(Constants.TEMP_DIR, "test-download-file-url"),
				new File(Constants.TEMP_DIR, "test-download-file-string")
		};

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