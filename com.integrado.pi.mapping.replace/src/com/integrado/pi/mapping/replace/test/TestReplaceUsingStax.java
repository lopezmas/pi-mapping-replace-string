package com.integrado.pi.mapping.replace.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.integrado.pi.mapping.replace.utils.ReplaceUsingStax;

public class TestReplaceUsingStax {

	  public static void main(String[] args) throws Exception {

	        
	        //Alternatively use a file to test from a big file. 
	        InputStream fis = new FileInputStream("C:/tmp/ORDERS.xml");
	        FileOutputStream fos = new FileOutputStream("C:/tmp/ORDERS.out.xml");

	        ReplaceUsingStax replaceUsingStax = new ReplaceUsingStax();
	        replaceUsingStax.reduceUsingStax(fis, fos, "(\r|\n)", "");
	     	   
	     	  fos.flush();
	     	  fos.close();
	        
	    }
	  
}
