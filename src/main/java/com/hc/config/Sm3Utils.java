package com.hc.config;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Arrays;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

public class Sm3Utils {

	private static final String ENCODING = "UTF-8";
	static {
		Security.addProvider(new BouncyCastleProvider());
	}
	public static String encrypt(String paramStr) {
		String resultHexString = "";
		try {
			byte[] srcData = paramStr.getBytes(ENCODING);
			byte[] resultHash = hash(srcData);
			resultHexString = ByteUtils.toHexString(resultHash);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return resultHexString;
	}
	public static byte[] hash(byte[] srcData) {
		SM3Digest digest = new SM3Digest();
		digest.update(srcData,0,srcData.length);
		byte[] hash = new byte[digest.getDigestSize()];
		digest.doFinal(hash, 0);
		return hash;
	}

	/**
	 *
	 * @param srcStr mingwen
	 * @param sm3HexString miwen
	 * @return
	 */
	public static boolean verify(String srcStr,String sm3HexString)
	{
		boolean flag = false;
		try {
			byte[] srcData = srcStr.getBytes("utf-8");
			byte[] sm3Hash = ByteUtils.fromHexString(sm3HexString);
			byte[] newHash = hash(srcData);
			if(Arrays.equals(newHash,sm3Hash)) {
				flag = true;
			}
		}
		catch(Exception e) {

		}
		return flag;
	}

	public static void main(String[] args) {

	}
}
