package com.pocolifo.commons.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;

import com.pocolifo.commons.HttpCommons;
import com.pocolifo.commons.StreamCommons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HttpCommonsTest {
	public byte[] getDownloadedFileContent() throws IOException {
		return StreamCommons.readInputStream(Objects.requireNonNull(
				this.getClass().getResourceAsStream("/jsonplaceholder-typicode-users.json")));
	}

	@Test
	void getUrl() throws MalformedURLException {
		Assertions.assertEquals(HttpCommons.getUrl(Constants.DOWNLOAD_URL), new URL(Constants.DOWNLOAD_URL));
	}

	@Test
	void downloadString() throws IOException {
		Assertions.assertArrayEquals(getDownloadedFileContent(), HttpCommons.download(Constants.DOWNLOAD_URL));
	}

	@Test
	void downloadUrl() throws IOException {
		Assertions.assertArrayEquals(getDownloadedFileContent(),
				HttpCommons.download(HttpCommons.getUrl(Constants.DOWNLOAD_URL)));
	}

	@Test
	void downloadFileString() throws IOException {
		File tempFile = new File(Constants.TEMP_DIR, "test-download-file-string");
		HttpCommons.downloadFile(Constants.DOWNLOAD_URL, tempFile);
		Assertions.assertArrayEquals(getDownloadedFileContent(), Files.readAllBytes(tempFile.toPath()));
	}

	@Test
	void downloadFileUrl() throws IOException {
		File tempFile = new File(Constants.TEMP_DIR, "test-download-file-url");
		HttpCommons.downloadFile(HttpCommons.getUrl(Constants.DOWNLOAD_URL), tempFile);
		Assertions.assertArrayEquals(getDownloadedFileContent(), Files.readAllBytes(tempFile.toPath()));
	}
}