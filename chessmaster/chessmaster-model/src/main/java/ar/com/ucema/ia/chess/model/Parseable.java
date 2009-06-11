package ar.com.ucema.ia.chess.model;

import org.dom4j.Element;


/**
 * Define los metodos basicos para los objetos que quieran ser parseados a XML. 
 * @author Mat�as Su�rez
 */
public interface Parseable {

	/**
	 * Parses it self to XML and attaches it self to the root element. 
	 * @see Element
	 * @param the XML root element
	 * @return the chess piece as XML
	 */
	Element toXML(Element root);
	
}
