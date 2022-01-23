package com.pocolifo.commons;

import java.io.*;

import org.jetbrains.annotations.NotNull;

public class StreamCommons {
	public static final int BUFFER_LENGTH = 1024;

	/**
	 * Read an InputStream to a byte array
	 *
	 * @param stream InputStream to read
	 * @return Byte array of the stream that was read
	 * @throws IOException Thrown if the stream can't be read
	 */
	public static byte[] readInputStream(@NotNull InputStream stream) throws IOException {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[BUFFER_LENGTH];
			int length = stream.read(buffer);

			while (length >= 0) {
				outputStream.write(buffer, 0, length);
				length = stream.read(buffer);
			}

			return outputStream.toByteArray();
		}
	}

	/**
	 * Read an InputStream to a string
	 *
	 * @param stream InputStream to read
	 * @return String of what was read
	 * @throws IOException There was trouble reading the InputStream
	 */
	public static String readInputStreamToString(InputStream stream) throws IOException {
		return new String(readInputStream(stream));
	}
}
