package com.pocolifo.commons.test;

import java.util.Objects;
import java.util.function.BooleanSupplier;

import com.pocolifo.commons.OperatingSystemCommons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestSkippedException;
import static org.junit.jupiter.api.Assertions.*;

class OperatingSystemCommonsTest {
	@Test
	void getHostOperatingSystem() {
		String os = System.getenv("RUNNER_OS");

		Assumptions.assumeTrue(os != null, "RUNNER_OS environment variable is not set.");
		Assertions.assertTrue(Objects.requireNonNull(
				OperatingSystemCommons.getHostOperatingSystem()).name().equalsIgnoreCase(os));
	}
}