package com.pocolifo.commons;

public class OperatingSystemCommons {
	public enum OperatingSystems {
		LINUX("linux", "Linux"),
		WINDOWS("windows", "Windows"),
		MACOS("mac", "macOS"),
		SOLARIS("sunos", "SunOS"),
		FREEBSD("freebsd", "FreeBSD");

		private final String id;
		public final String niceName;

		OperatingSystems(String id, String niceName) {
			this.id = id;
			this.niceName = niceName;
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
