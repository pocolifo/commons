package com.pocolifo.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingCommons {
	private static final int MAX_HEX_CHAR = 0xFF;
	private static final char[] HEX_CHARACTERS = "0123456789abcdef".toCharArray();
	private static final String[] HEX = new String[HEX_CHARACTERS.length * HEX_CHARACTERS.length];

	static {
		int i = 0;

		for (char x : HEX_CHARACTERS) {
			for (char y : HEX_CHARACTERS) {
				HEX[i] = String.valueOf(x) + y;
				i++;
			}
		}
	}

	private static byte[] doAlgorithm(String algorithm, byte[] in) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			digest.reset();
			digest.update(in);
			return digest.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] sha1(byte[] in) {
		return doAlgorithm("SHA-1", in);
	}

	public static byte[] sha256(byte[] in) {
		return doAlgorithm("SHA-256", in);
	}

	public static byte[] sha384(byte[] in) {
		return doAlgorithm("SHA-384", in);
	}

	public static byte[] sha512(byte[] in) {
		return doAlgorithm("SHA-512", in);
	}

	public static byte[] md5(byte[] in) {
		return doAlgorithm("MD5", in);
	}

	public static String asHex(byte in) {
		return HEX[in & MAX_HEX_CHAR];
	}

	public static String asHex(byte[] in) {
		String[] s = new String[in.length];

		for (int i = 0; in.length > i; i++) {
			s[i] = asHex(in[i]);
		}

		return String.join("", s);
	}
}
