package ar.com.ucema.ia.chess.xml.fixtures;

import junit.framework.TestCase;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import ar.com.ucema.ia.chess.model.pieces.ChessPiece;
import ar.com.ucema.ia.chess.model.xml.XMLConstants;
import ar.com.ucema.ia.chess.utils.XMLPrinter;

/**
 * Test base para todo lo referente a XML 
 * @author Matías Suárez
 */
public class XMLBaseTestCase extends TestCase {
	protected Document doc;
	protected Element root;
	
	public XMLBaseTestCase() {
		super();
		doc = DocumentHelper.createDocument();
		root = doc.addElement("root");
	}

	
	
	public void assertToXML(Element e, ChessPiece piece) {
		assertNotNull(e);
		assertEquals(piece.getPieceNameXMLTag(), e.attributeValue(XMLConstants.ATTRIBUTE_TYPE));
		assertEquals(piece.getColor().toString(), e.attributeValue(XMLConstants.ATTRIBUTE_COLOR));
		printDocument();
	}
	
	public void printDocument() {
		XMLPrinter.printToConsole(doc);
	}
	
}
