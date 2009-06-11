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
public class Queen extends ChessPiece {

	public Queen(Color color) {
		super(color);
	}

	public static Queen createBlackQueen() {
		return new Queen(Color.Black);
	}

	public static Queen createWhiteQueen() {
		return new Queen(Color.Black);
	}

	@Override
	public String toString() {
		return this.getColor() + " Queen";
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

		// analizo el movimiento por diagonales
		ChessColumns columns = new ChessColumns();
		int hMovement = Math.abs(move.getFrom().getRow() - move.getTo().getRow());
		int vMovement = Math.abs(columns.getColumnNumber(move.getFrom().getColumn()) - columns.getColumnNumber(move.getTo().getColumn()));
		
		// verifico que no se quiera mover en forma diagonal.
		if (hMovement == vMovement ) {
			// si no hay ninguna pieza en el medio, es un movimiento valido.
			boolean isThereAnyPieceInBetween = move.getBoard().isThereAnyChessPieceIn(move.getBoard().getChessCellInBetween(move.getFrom(), move.getTo()), move.getBoard().getChessCellAt(move.getFrom()).getPiece()); 
			
			if ( isThereAnyPieceInBetween && move.getBoard().getChessCellAt(move.getTo()).getPiece() != null ) {
				return true;
			}
			
			return !isThereAnyPieceInBetween;
		}


		//analizo el movimiento por columna
		if (movedByColumn(move) || (movedByRow(move) && move.getTo().getRow() <= 7)) {
			// si no hay ninguna pieza en el medio, es un movimiento valido.
			return !move.getBoard().isThereAnyChessPieceIn(move.getBoard().getChessCellInBetween(move.getFrom(), move.getTo()), move.getBoard().getChessCellAt(move.getFrom()).getPiece());
		}

		return false;
	}

	private Boolean movedByColumn(ChessMovement move) {
		return (move.getFrom().getColumn().equals(move.getTo().getColumn()));
	}

	private Boolean movedByRow(ChessMovement move) {
		return (move.getFrom().getRow().equals(move.getTo().getRow()));
	}

	@Override
	public String getPieceNameXMLTag() {
		return XMLConstants.TAG_QUEEN;
	}

	@Override
	public Integer getPieceValue() {
		return 15;
	}

}