package com.mynrma.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static com.mynrma.executionEngine.DriverScript.logger;

public class CryptPasswordUtility {
	
private static MessageDigest md;

public static String cryptPassword(String password) {
	
	try {
		
		md = MessageDigest.getInstance("MD5");
		byte[] passBytes = password.getBytes();	
		md.reset();	
		byte[] digested = md.digest(passBytes);	
		StringBuffer sb = new StringBuffer();
        for(int i=0;i<digested.length;i++){
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        return sb.toString();
		
	} catch(NoSuchAlgorithmException e) {
		
		logger.info("Unable to encrypt the password");
	
	}
	
	return null;
}
}
