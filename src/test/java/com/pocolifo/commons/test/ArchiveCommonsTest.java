package com.pocolifo.commons.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.pocolifo.commons.ArchiveCommons;
import com.pocolifo.commons.ResourceCommons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class ArchiveCommonsTest {
	@Test
	void zipSlip() {
		Assertions.assertThrows(SecurityException.class, () -> {
			ArchiveCommons.extractZip(ResourceCommons.getResourceAsFile(this.getClass(), "zip-slip.zip"),
					Constants.TEMP_DIR);
		});
	}

	@Test
	void zipSlipWindows() {
		Assertions.assertThrows(SecurityException.class, () -> {
			ArchiveCommons.extractZip(ResourceCommons.getResourceAsFile(this.getClass(), "zip-slip-win.zip"),
					Constants.TEMP_DIR);
		});
	}

	@Test
	void extractZip1() throws IOException {
		ArchiveCommons.extractZip(Constants.ZIP_FILE, new File(Constants.TEMP_DIR, "extract-1"), s -> "renamed_" + s,
				false, "sample.txt");
	}

	@Test
	void extractZip2() throws IOException {
		ArchiveCommons.extractZip(Constants.ZIP_FILE, new File(Constants.TEMP_DIR, "extract-2"), true,
				"sample.txt");
	}

	@Test
	void extractZip3() throws IOException {
		ArchiveCommons.extractZip(Constants.ZIP_FILE, new File(Constants.TEMP_DIR, "extract-3"));
	}

	@Test
	void extractZip4() throws IOException {
		File toExtract = new File(Constants.TEMP_DIR, "extract-4.zip");
		Files.copy(Constants.ZIP_FILE.toPath(), toExtract.toPath());
		ArchiveCommons.extractZip(toExtract);
	}

	@Test
	void extractZipWithDirectories() throws IOException {
		File extraction = new File(Constants.TEMP_DIR, "extract-dirs");
		extraction.mkdirs();

		ArchiveCommons.extractZip(Constants.ZIP_FILE_WITH_DIRS, extraction);

		File sample = new File(extraction, "sample.txt");

		File sample1_dir = new File(extraction, "sample1");
		File sample1_file = new File(sample1_dir,"sample1.txt");

		File sample2_dir = new File(extraction, "sample2");
		File sample2_file = new File(sample2_dir, "sample2.txt");

		Assertions.assertTrue(
				sample.isFile()
				&& sample1_dir.isDirectory() && sample1_file.isFile()
				&& sample2_dir.isDirectory() && sample2_file.isFile()
		);
	}
}