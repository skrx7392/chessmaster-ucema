package ar.com.ucema.ia.chess.xml.fixtures;

import org.dom4j.Element;

import ar.com.ucema.ia.chess.model.Color;
import ar.com.ucema.ia.chess.model.pieces.Pawn;

public class PawnParsingTestCase extends XMLBaseTestCase {

	public void testToXML() {
		Pawn blackPawn = new Pawn(Color.Black);
		Element pawnAsXML = blackPawn.toXML(root);
		assertToXML(pawnAsXML, blackPawn);
	}
	
}
