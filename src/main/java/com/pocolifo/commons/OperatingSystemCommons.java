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

	public static OperatingSystems getHostOperatingSystem() {
		String osName = System.getProperty("os.name").toLowerCase();

		for (OperatingSystems os : OperatingSystems.values()) {
			if (osName.contains(os.id)) return os;
		}

		return null;
	}
}
