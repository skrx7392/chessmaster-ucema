package ar.com.ucema.ia.chess.xml.fixtures;

import org.dom4j.Element;

import ar.com.ucema.ia.chess.model.ChessCell;
import ar.com.ucema.ia.chess.model.Color;
import ar.com.ucema.ia.chess.model.pieces.Pawn;
import ar.com.ucema.ia.chess.model.xml.XMLConstants;

public class ChessCellParsingTestCase extends XMLBaseTestCase {

	public void testToXMLWithNoChessPiece() {
		ChessCell aPiece = new ChessCell("A1", 1);
		Element e = aPiece.toXML(root);

		assertNotNull(e);
		assertEquals(XMLConstants.TAG_CHESS_PIECE, e.getName());
		assertEquals("A1", e.attributeValue(XMLConstants.ATTRIBUTE_COLUMN));
		assertEquals(1, Integer.parseInt(e.attributeValue(XMLConstants.ATTRIBUTE_ROW)));
		printDocument();

	}
	
	public void testToXMLWithChessPiece() {
		Pawn piece = new Pawn(Color.White);
		ChessCell aPiece = new ChessCell("A1", 1, piece);
		Element e = aPiece.toXML(root);
		
		assertNotNull(e);
		assertEquals(XMLConstants.TAG_CHESS_PIECE, e.getName());
		assertEquals("A1", e.attributeValue(XMLConstants.ATTRIBUTE_COLUMN));
		assertEquals(1, Integer.parseInt(e.attributeValue(XMLConstants.ATTRIBUTE_ROW)));
		assertToXML(e, piece);
		printDocument();
	}

}
