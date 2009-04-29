package ar.com.ucema.ia.chess.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XMLPrinter {
	
	public static void printToConsole(Document doc) {
		Writer out = new BufferedWriter(new OutputStreamWriter(System.out));
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(out, format);
	    try {
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	
}
