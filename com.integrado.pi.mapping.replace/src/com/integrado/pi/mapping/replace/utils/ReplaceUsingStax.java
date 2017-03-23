package com.integrado.pi.mapping.replace.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class ReplaceUsingStax {

	/**
	 * 
	 * Replaces content in the node values of an XML Document. Uses Stax for streaming the content rather than loading it into memory
	 * 
	 * @param is
	 * @param os
	 * @param regex
	 * @param replaceWith
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	public void reduceUsingStax(InputStream is, OutputStream os, String regex , String replaceWith) throws XMLStreamException, IOException{
		
		XMLInputFactory inFactory = XMLInputFactory.newInstance();
	    XMLEventReader  eventReader = inFactory.createXMLEventReader(is);
	    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
//	    XMLEventReader eventReader = inFactory.createXMLEventReader(new FileInputStream("C:/tmp/veolia_master_data/EQ_QLD_FULL_v2/stax/EQ_QLD_FULL_Reduced_NOT_Valid.xml"));
	    XMLOutputFactory factory = XMLOutputFactory.newInstance();
	    XMLEventWriter writer = factory.createXMLEventWriter(os);
	    while (eventReader.hasNext()) {
	        XMLEvent event = eventReader.nextEvent();
	        switch (event.getEventType()) {
				case XMLStreamConstants.CHARACTERS:
					Characters characters = event.asCharacters();
					String charactersString = characters.getData();
					if(characters!=null){
						charactersString = charactersString.trim();
//						charactersString = charactersString.replaceAll(">(\n\r|\r|\n)", "");						
						charactersString = charactersString.replaceAll(regex, replaceWith);						
//						charactersString = charactersString.replaceAll("(\r|\n)", "");						
//						charactersString = charactersString.replace("\n", "").replace("\r", "");
					}
					writer.add(eventFactory.createCharacters(charactersString));
					break;
				default:
					writer.add(event);
					break;
	        }
	    	
	    }
	    
	    writer.close();
	}
	
}
