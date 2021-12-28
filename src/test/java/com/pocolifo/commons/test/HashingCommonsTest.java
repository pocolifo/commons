package com.pocolifo.commons.test;

import java.nio.charset.StandardCharsets;

import com.pocolifo.commons.HashingCommons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashingCommonsTest {
	public static final byte[] SAMPLE = "Hello world!".getBytes(StandardCharsets.UTF_8);

	@Test
	void sha1() {
		Assertions.assertEquals("d3486ae9136e7856bc42212385ea797094475802",
				HashingCommons.asHex(HashingCommons.sha1(SAMPLE)));
	}

	@Test
	void sha256() {
		Assertions.assertEquals("c0535e4be2b79ffd93291305436bf889314e4a3faec05ecffcbb7df31ad9e51a",
				HashingCommons.asHex(HashingCommons.sha256(SAMPLE)));
	}

	@Test
	void sha384() {
		Assertions.assertEquals(
				"86255fa2c36e4b30969eae17dc34c772cbebdfc58b58403900be87614eb1a34b8780263f255eb5e65ca9bbb8641cccfe",
				HashingCommons.asHex(HashingCommons.sha384(SAMPLE)));
	}

	@Test
	void sha512() {
		Assertions.assertEquals(
				"f6cde2a0f819314cdde55fc227d8d7dae3d28cc556222a0a8ad66d91ccad4aad6094f517a2182360c9aacf6a3dc323162cb6fd8cdffedb0fe038f55e85ffb5b6",
				HashingCommons.asHex(HashingCommons.sha512(SAMPLE)));
	}

	@Test
	void md5() {
		Assertions.assertEquals(
				"86fb269d190d2c85f6e0468ceca42a20",
				HashingCommons.asHex(HashingCommons.md5(SAMPLE)));
	}
}