package com.pocolifo.commons;

public class OperatingSystemCommons {
	public enum OperatingSystems {
		LINUX("linux"),
		WINDOWS("windows"),
		MACOS("mac"),
		SOLARIS("sunos"),
		FREEBSD("freebsd");

		private final String id;

		OperatingSystems(String id) {
			this.id = id;
		}
	}

	/**
	 * Gets the operating system of the host computer
	 *
	 * @return The current operating system of the host
	 * @see OperatingSystems
	 */
	public static OperatingSystems getHostOperatingSystem() {
		String osName = System.getProperty("os.name").toLowerCase();

		for (OperatingSystems os : OperatingSystems.values()) {
			if (osName.contains(os.id)) return os;
		}

		return null;
	}
}
