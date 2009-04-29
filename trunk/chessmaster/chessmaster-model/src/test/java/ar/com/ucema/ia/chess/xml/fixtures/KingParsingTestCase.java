package ar.com.ucema.ia.chess.xml.fixtures;

import org.dom4j.Element;

import ar.com.ucema.ia.chess.model.Color;
import ar.com.ucema.ia.chess.model.pieces.King;

public class KingParsingTestCase extends XMLBaseTestCase {


	public void testToXML() {
		King blackKing = new King(Color.Black);
		Element kingAsXML = blackKing.toXML(root);
		assertToXML(kingAsXML, blackKing);
	}

}
