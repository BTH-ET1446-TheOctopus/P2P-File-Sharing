package backend;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.Backend;
import backend.json.Swarm;
//import pack1.test1;

public class hmac {

//
//	
//
//
//		public static void main(String[] args) throws NoSuchAlgorithmException{
//			
		
		String abc;
		//String hmac (byte [] abc) throws NoSuchAlgorithmException{
		String s = new String(abc);
		 // public static void main(String[] args) throws NoSuchAlgorithmException; {
		     //   System.out.println(hmac("test string to sha1"));
				//return s;
		    
		     
		String hmac(byte[] abc1) throws NoSuchAlgorithmException{//throws NoSuchAlgorithmException {
		        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		        String input = null;
				byte[] result = mDigest.digest(input.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < result.length; i++) {
		            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		        }
		    //}
		         
		        
		
			// backend = Backend.getInstance;
			
			    //MessageDigest md = null;
				//Formatter formatter = new Formatter();
				 
				 
				//  Formatter formatter;
				//formatter.format("%02x", sb);
				  
				   // return formatter.toString();
				return sb.toString();
			    
				}
}

