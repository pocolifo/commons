package com.pocolifo.commons.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import com.pocolifo.commons.StreamCommons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StreamCommonsTest {
	@Test
	void readInputStream() throws IOException {
		byte[] bytes = StreamCommons.readInputStream(Objects.requireNonNull(
				this.getClass().getResourceAsStream("/sample.txt")));

		Assertions.assertArrayEquals(Constants.SAMPLE_TXT.getBytes(StandardCharsets.UTF_8), bytes);
	}

	@Test
	void readInputStreamToString() throws IOException {
		String str = StreamCommons.readInputStreamToString(Objects.requireNonNull(
				this.getClass().getResourceAsStream("/sample.txt")));

		Assertions.assertEquals(str, Constants.SAMPLE_TXT);
	}
}