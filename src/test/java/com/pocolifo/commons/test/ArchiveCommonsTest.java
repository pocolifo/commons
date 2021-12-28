package com.pocolifo.commons.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.pocolifo.commons.ArchiveCommons;
import com.pocolifo.commons.ResourceCommons;
import org.junit.jupiter.api.Assertions;
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
}