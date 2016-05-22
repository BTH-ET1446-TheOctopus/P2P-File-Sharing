package test;

import static org.junit.Assert.assertNotNull;

import backend.Settings;

public class testSettings {
	
	@org.junit.Test
	/**
	 * Test that the function don't return null, it will return a string
	 * whit the ntp time.
	 */
	public void getNTPtime(){
		System.out.println(Settings.getNTP());
		assertNotNull(Settings.getNTP());
		
	}
}
