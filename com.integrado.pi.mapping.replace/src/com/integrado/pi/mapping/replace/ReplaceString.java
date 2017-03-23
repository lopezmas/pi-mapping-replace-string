package com.integrado.pi.mapping.replace;
import java.io.InputStream;
import java.io.OutputStream;

import com.integrado.pi.mapping.replace.utils.ReplacingInputStream;
import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;


public class ReplaceString extends AbstractTransformation{

		 String search_for = "";
		 String replace_with = "";
	     public void transform(TransformationInput input, TransformationOutput output)  
	               throws StreamTransformationException {  
	
	          getTrace().addInfo("JAVA Mapping replace function intiated");  
	          loadImportParameter(input);
	          
	            InputStream is = input.getInputPayload().getInputStream();
	            OutputStream os 	  = output.getOutputPayload().getOutputStream();
	            
	          replaceInInputStream(is,os);  
	     }  
	     
	     
	
	 	   
	   private void replaceInInputStream(InputStream is, OutputStream os){
		   try {

	          	            
	            InputStream ris = new ReplacingInputStream(is, search_for.getBytes("UTF-8"), replace_with.getBytes("UTF-8"));
	           
	            
	            int b;
		        while (-1 != (b = ris.read()))
		        	os.write(b);
	     	   
	     	   os.flush();
	     	   ris.close();
	        } catch (Exception e) {
	        	  getTrace().addWarning(  
	                         "Exception caught in replaceStringUsingStreams: "  
	                                   + e.toString());
	            e.printStackTrace();
	        }
	   }
	   
	   
	   
	   public void loadImportParameter(TransformationInput arg0) throws StreamTransformationException {
		   
		   search_for = arg0.getInputParameters().getString("search_for");
		   if(search_for == null ) throw new StreamTransformationException("--> Parameter search_for is NULL");
		   
		   replace_with	      = arg0.getInputParameters().getString("replace_with");
		   if(replace_with == null ) throw new StreamTransformationException("--> Parameter replace_with is NULL");
		   
		   
		   getTrace().addInfo("--> Found input parameter search_for: " + search_for);
		   getTrace().addInfo("--> Found input parameter replace_with: " + replace_with);
		   
	   }
	   



	
}
