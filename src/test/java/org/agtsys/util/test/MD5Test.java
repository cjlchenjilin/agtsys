package org.agtsys.util.test;

import static org.junit.Assert.*;

import org.agtsys.util.MD5;
import org.junit.Before;
import org.junit.Test;

public class MD5Test {
	//private MD5 md5;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testByteToHexS() {
		assertEquals("0a", MD5.byteToHexString(new Byte("10")));;
	}
	
	@Test
	public void testByteToHexString() {
		assertEquals("f6", MD5.byteToHexString(new Byte("-10")));;
	}
	@Test
	public void testbytesToHexString(){
		assertEquals("0a0b", MD5.bytesToHexString(new byte[]{10,11}));
	}
	@Test
	public void testmd5encode(){
		assertEquals("e10adc3949ba59abbe56e057f20f883e", MD5.md5encode("123456"));
	}
}
