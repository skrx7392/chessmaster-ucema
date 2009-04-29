package ar.com.ucema.ia.chess.xml.fixtures;

import org.dom4j.Element;

import ar.com.ucema.ia.chess.model.Color;
import ar.com.ucema.ia.chess.model.pieces.Bishop;

public class BishopParsingTestCase extends XMLBaseTestCase {

	public void testToXML() {
		Bishop blackBishop = new Bishop(Color.Black);
		Element bishopAsXML = blackBishop.toXML(root);
		assertToXML(bishopAsXML, blackBishop);
	}

}
