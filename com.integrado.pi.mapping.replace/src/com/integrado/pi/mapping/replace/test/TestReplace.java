package com.integrado.pi.mapping.replace.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.integrado.pi.mapping.replace.utils.ReplacingInputStream;

public class TestReplace {

	  public static void main(String[] args) throws Exception {
	        byte[] search = "searchfor".getBytes("UTF-8");
	        byte[] replacement = "abc".getBytes("UTF-8");
	        
	        //Alternatively use a file to test from a big file. 
	        InputStream fis = new FileInputStream("C:/tmp/ORDERS.xml");
	        FileOutputStream fos = new FileOutputStream("C:/tmp/ORDERS.out.xml");
	        InputStream ris = new ReplacingInputStream(fis, search, replacement);
	           
          
	        int b;
	        while (-1 != (b = ris.read()))
	        	fos.write(b);
	     	   
	     	  fos.flush();
	     	   ris.close();
	     	  fos.close();
	        
	    }
	  
}
