package ar.com.ucema.ia.chess.model.pieces;

import ar.com.ucema.ia.chess.model.ChessBoard;
import ar.com.ucema.ia.chess.model.ChessColumns;
import ar.com.ucema.ia.chess.model.ChessMovement;
import ar.com.ucema.ia.chess.model.Color;
import ar.com.ucema.ia.chess.model.xml.XMLConstants;

/**
 * 
 * @author Matías Suárez
 */
public class King extends ChessPiece {

	public King(Color color) {
		super(color);
	}
	
	public static King createBlackKing() {
		return new King(Color.Black);
	}

	public static King createWhiteKing() {
		return new King(Color.Black);
	}


	@Override
	public boolean canMove(ChessBoard board) {
		return false;
	}

	@Override
	public boolean isValidMove(ChessMovement move) {
		// siempre se tiene que llamar debido a que hace la validacion de rey en jaque.
		if ( super.isValidMove(move) == false )
			return false;

		ChessColumns columns = new ChessColumns();
		int rowDifference = (move.getFrom().getRow() < move.getTo().getRow()) ? move.getTo().getRow() - move.getFrom().getRow() : move.getFrom().getRow() - move.getTo().getRow(); 
		int colDifference = columns.getColumnDifferenceBetween(move.getFrom().getColumn(), move.getTo().getColumn());
	
		// verifica que no se quiera mover mas de una posicion.
		if ( (rowDifference <= 1) && (colDifference <= 1) ) { 
			return true;
		} 

		return false;
	}

	@Override
	public String getPieceNameXMLTag() {
		return XMLConstants.TAG_QUEEN;
	}

	@Override
	public Integer getPieceValue() {
		return 20;
	}

}