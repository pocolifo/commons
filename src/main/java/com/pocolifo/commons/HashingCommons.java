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

	/**
	 * Get the SHA-1 hash of bytes
	 *
	 * @param in Bytes in
	 * @return The hash as bytes
	 */
	public static byte[] sha1(byte[] in) {
		return doAlgorithm("SHA-1", in);
	}

	/**
	 * Get the SHA-256 hash of bytes
	 *
	 * @param in Bytes in
	 * @return The hash as bytes
	 */
	public static byte[] sha256(byte[] in) {
		return doAlgorithm("SHA-256", in);
	}

	/**
	 * Get the SHA-384 hash of bytes
	 *
	 * @param in Bytes in
	 * @return The hash as bytes
	 */
	public static byte[] sha384(byte[] in) {
		return doAlgorithm("SHA-384", in);
	}

	/**
	 * Get the SHA-512 hash of bytes
	 *
	 * @param in Bytes in
	 * @return The hash as bytes
	 */
	public static byte[] sha512(byte[] in) {
		return doAlgorithm("SHA-512", in);
	}

	/**
	 * Get the MD-5 hash of bytes
	 *
	 * @param in Bytes in
	 * @return The hash as bytes
	 */
	public static byte[] md5(byte[] in) {
		return doAlgorithm("MD5", in);
	}

	/**
	 * Convert a byte to its hexadecimal form
	 *
	 * @param in Byte to convert
	 * @return The byte as hex
	 */
	public static String asHex(byte in) {
		return HEX[in & MAX_HEX_CHAR];
	}

	/**
	 * Convert a byte array to its hexadecimal form
	 *
	 * @param in Byte array to convert
	 * @return The byte array as a hex string
	 */
	public static String asHex(byte[] in) {
		String[] s = new String[in.length];

		for (int i = 0; in.length > i; i++) {
			s[i] = asHex(in[i]);
		}

		return String.join("", s);
	}
}
