package ar.com.ucema.ia.chess.xml.fixtures;

import org.dom4j.Element;

import ar.com.ucema.ia.chess.model.Color;
import ar.com.ucema.ia.chess.model.pieces.Rook;

public class RookParsingTestCase extends XMLBaseTestCase {

	public void testToXML() {
		Rook blackRook = new Rook(Color.Black);
		Element rookAsXML = blackRook.toXML(root);
		assertToXML(rookAsXML, blackRook);
	}

}
