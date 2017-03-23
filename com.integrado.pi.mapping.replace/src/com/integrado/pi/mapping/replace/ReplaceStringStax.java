package com.integrado.pi.mapping.replace;
import java.io.InputStream;
import java.io.OutputStream;

import com.integrado.pi.mapping.replace.utils.ReplaceUsingStax;
import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;


public class ReplaceStringStax extends AbstractTransformation{

		 String regex = "";
		 String replace_with = "";
	     public void transform(TransformationInput input, TransformationOutput output)  
	               throws StreamTransformationException {  
	
	          getTrace().addInfo("JAVA Mapping replace function intiated");  
	          loadImportParameter(input);
	          
	            InputStream is = input.getInputPayload().getInputStream();
	            OutputStream os 	  = output.getOutputPayload().getOutputStream();
	            ReplaceUsingStax replaceUsingStax = new ReplaceUsingStax();
	            try {
					replaceUsingStax.reduceUsingStax(is, os, regex, replace_with);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new StreamTransformationException(e.getMessage(),e);
				} 	              
	     }  
	   
	   public void loadImportParameter(TransformationInput arg0) throws StreamTransformationException {
		   
		   regex = arg0.getInputParameters().getString("regex");
		   if(regex == null ) throw new StreamTransformationException("--> Parameter regex is NULL");
		   
		   replace_with	      = arg0.getInputParameters().getString("replace_with");
		   if(replace_with == null ) throw new StreamTransformationException("--> Parameter replace_with is NULL");
		   
		   getTrace().addInfo("--> Found input parameter regex: " + regex);
		   getTrace().addInfo("--> Found input parameter replace_with: " + replace_with);
		   
	   }
	   



	
}
