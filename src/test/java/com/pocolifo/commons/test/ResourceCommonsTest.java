package com.pocolifo.commons.test;

import java.net.URISyntaxException;

import com.pocolifo.commons.ResourceCommons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ResourceCommonsTest {
	@Test
	void getResourceAsFile() throws URISyntaxException {
		Assertions.assertNotNull(ResourceCommons.getResourceAsFile(this.getClass(), "sample.txt"));
	}
}