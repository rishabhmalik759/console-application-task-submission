package com.rm.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rm.utils.Triplet;

public class TripletTest {

	Triplet triplet;
	
	@Before  
    public void setUp() throws Exception {  

		String str = "TXC#t#OQR";
		triplet = new Triplet(str);
    }
	
	@Test
	public void testGetLeft() {
		
		assertEquals("TXC", triplet.getLeft());
	}
	
	@Test
	public void testGetRight() {
		
		assertEquals("OQR", triplet.getRight());
	}
	
	@Test
	public void testGetMessage() {
		
		assertEquals("t", triplet.getMessage());
	}
	
	@Test
	public void testGetNext() {
		
		assertEquals("RQO", triplet.getNext());
	}
	
	@After
	public void tearDown() {
		triplet = null;
	}
}
