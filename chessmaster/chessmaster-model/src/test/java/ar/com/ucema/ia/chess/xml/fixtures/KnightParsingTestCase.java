package ar.com.ucema.ia.chess.xml.fixtures;

import org.dom4j.Element;

import ar.com.ucema.ia.chess.model.Color;
import ar.com.ucema.ia.chess.model.pieces.Knight;

public class KnightParsingTestCase extends XMLBaseTestCase {

	
	public void testToXML() {
		Knight blackKnight = new Knight(Color.White);
		Element knightAsXML = blackKnight.toXML(root);
		assertToXML(knightAsXML, blackKnight);
	}

}
