package ar.com.ucema.ia.chess.xml.fixtures;

import org.dom4j.Element;

import ar.com.ucema.ia.chess.model.Color;
import ar.com.ucema.ia.chess.model.pieces.Queen;

public class QueenParsingTestCase extends XMLBaseTestCase {

	
	public void testToXML() {
		Queen blackQueen = new Queen(Color.Black);
		Element queenXML = blackQueen.toXML(root);
		assertToXML(queenXML, blackQueen);
	}

}
