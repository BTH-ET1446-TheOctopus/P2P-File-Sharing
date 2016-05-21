package backend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import backend.readfile;

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */

public class Sha1 {
	
String Shafile(String filename) throws NoSuchAlgorithmException, IOException {
	readfile rd = new readfile();
	//String file = filename;
	//byte[] fileArray;
    return sha1(rd.filereader(filename));
}
String ShaString(String str) {
	return null;
}
String shaByte(byte[]b) {
	return null;
}
static String sha1(String input) throws NoSuchAlgorithmException {
MessageDigest mDigest = MessageDigest.getInstance("SHA1");//  byte[] result = mDigest.digest(input.getBytes());
byte[] result = mDigest.digest(input.getBytes());
 StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < result.length; i++) {
	            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	        }
	         
	        return sb.toString();
	    }
	}	

		



