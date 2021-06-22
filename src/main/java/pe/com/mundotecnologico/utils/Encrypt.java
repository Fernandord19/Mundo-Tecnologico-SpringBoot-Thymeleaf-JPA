package pe.com.mundotecnologico.utils;

import java.io.Serializable;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String key = "QN6I55E#P4O7L*MHo14ND@8kxxqZsfUt";
	private static final String salt = "iZB4&az*fup%K4uFerCjYdqHkiAKC6N*";
	
	private SecretKey secretKeyTemp;
	
	public Encrypt() {
		SecretKeyFactory secretKeyFactory;
		KeySpec keySpec;
		
		try {
			secretKeyFactory =SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			keySpec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), 65536, 256);
			secretKeyTemp = secretKeyFactory.generateSecret(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getAES(String data) {
		byte[] iv = new byte[16];
		try {
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			SecretKey secretKey = new SecretKeySpec(secretKeyTemp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
			return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getAESDecrypt(String data) {
		byte[] iv = new byte[16];
		try {
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			SecretKey secretKey = new SecretKeySpec(secretKeyTemp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
