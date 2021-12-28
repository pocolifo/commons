package com.pocolifo.commons.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.nio.file.Files;

import com.pocolifo.commons.ResourceCommons;

public class Constants {
	public static final String SAMPLE_TXT = "Sample text";
	public static final String DOWNLOAD_URL = "https://jsonplaceholder.typicode.com/users";
	public static final String[][] SAMPLE_FILE_PATHS = {
			{"C:\\Users\\someone\\Desktop", "C:/Users/someone/Desktop"},
			{"/home/someone/Desktop", "/home/someone/Desktop"}
	};

	public static File TEMP_DIR;
	public static File ZIP_FILE;

	static {
		try {
			TEMP_DIR = Files.createTempDirectory("pocolifo-commons-test-temp").toFile();
			ZIP_FILE = ResourceCommons.getResourceAsFile(Constants.class, "sample.zip");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

		System.out.println("=== Testing environment ===");

		for (Field field : Constants.class.getDeclaredFields()) {
			try {
				field.setAccessible(true);
				System.out.println(field.getName() + ": " + field.get(null));
			} catch (IllegalAccessException e) {
				System.out.println(field.getName() + ": {UNKNOWN}");
				e.printStackTrace();
			}
		}

	}
}
