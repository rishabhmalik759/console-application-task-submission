package com.rm.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rm.utils.Decoder;
import com.rm.utils.TripletDecoder;

public class TripletDecoderTest {

	Decoder decoder;
	
	@Before  
    public void setUp() throws Exception {  

		// TODO Change the file path as per your configuration
		String filename = "./sample/HelloMessage.txt";
		File file = new File(filename);
		decoder = new TripletDecoder(file);
    }
	
	@Test
	public void testDecode1() {
		
		assertEquals("Hello", decoder.decode("UXH"));
	}
	
	@Test
	public void testDecode2() {
		
		assertEquals("llo", decoder.decode("MRK"));
	}

	@Test
	public void testDecode3() {
		// Initial doesnt exist
		assertEquals(null, decoder.decode("ABC"));
	}

	@Test
	public void testDecode4() {
		// Initial is null
		assertEquals(null, decoder.decode(null));
	}

	@After
	public void tearDown() {
		decoder = null;
	}
}
