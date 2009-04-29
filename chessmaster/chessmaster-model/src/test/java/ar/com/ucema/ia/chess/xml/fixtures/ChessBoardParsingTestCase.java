package ar.com.ucema.ia.chess.xml.fixtures;

import org.dom4j.Element;

import ar.com.ucema.ia.chess.model.ChessBoard;
import ar.com.ucema.ia.chess.model.xml.XMLConstants;

public class ChessBoardParsingTestCase extends XMLBaseTestCase {

	
	public void testToXMLWithNoChessPiece() {
		ChessBoard aBoard = new ChessBoard();
		Element e = aBoard.toXML(root);

		assertNotNull(e);
		assertEquals(XMLConstants.TAG_CHESS_BOARD, e.getName());
		printDocument();
	}
	
}
