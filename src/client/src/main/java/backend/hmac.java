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


	


		//public static void main(String[] args) throws NoSuchAlgorithmException;
		public String hmac (byte [] abc){
		String s = new String(abc);
		 // public static void main(String[] args) throws NoSuchAlgorithmException; {
		        System.out.println(sha1("test string to sha1"));
		    }
		     
		    static String sha1(String input) throws NoSuchAlgorithmException {
		        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		        byte[] result = mDigest.digest(input.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < result.length; i++) {
		            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		        }
		         
		        return sb.toString();
		    
		
			// backend = Backend.getInstance;
			
			    //MessageDigest md = null;
				//Formatter formatter = new Formatter();
				 
				 
				  formatter.format("%02x", sb);
				  
				   // return formatter.toString();
				}
}


			

		


