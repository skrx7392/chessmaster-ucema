package ar.com.ucema.ia.chess.model.pieces;

import java.util.List;

import ar.com.ucema.ia.chess.model.ChessBoard;
import ar.com.ucema.ia.chess.model.ChessCell;
import ar.com.ucema.ia.chess.model.ChessMovement;
import ar.com.ucema.ia.chess.model.Color;
import ar.com.ucema.ia.chess.model.xml.XMLConstants;

public class Rook extends ChessPiece {

	public Rook(Color color) {
		super(color);
	}

	@Override
	public boolean canMove(ChessBoard board) {
		return false;
	}

	@Override
	public boolean eat(ChessCell to) {
		return false;
	}

	@Override
	public boolean isValidMove(ChessMovement move) {
		// siempre se tiene que llamar debido a que hace la validacion de rey en jaque.
		if ( super.isValidMove(move) == false )
			return false;

		// verifico que no se quiera mover en forma diagonal.
		if ( movedByColumn(move) && movedByRow(move) )
			return false;
		
		// verifico que la posicion a donde quiere ir, no haya una pieza del mismo color
		if ( move.getBoard().getChessCellAt(move.getTo()).getPiece() != null )
			if ( move.getBoard().getChessCellAt(move.getFrom()).getPiece().getColor().equals(move.getBoard().getChessCellAt(move.getTo()).getPiece().getColor()))
				return false;
		
		//analizo el movimiento por columna
		if (movedByColumn(move) || (movedByRow(move) && move.getTo().getRow() <= 7)) {
			
			// si no hay ninguna pieza en el medio, es un movimiento valido.
			return !isThereAnyChessPieceIn(move.getBoard().getChessCellInBetween(move.getFrom(), move.getTo()), move.getBoard().getChessCellAt(move.getFrom()).getPiece());
		}

		return false;
	}
	
	/**
	 * Checks out if there is any piece in the cells.
	 * @param cells all the chess posible cells.
	 * @param thePiece the piece in which the movement was made.
	 * @return true if there is a chess piece between the source and the destiny, false otherwise.
	 */
	private Boolean isThereAnyChessPieceIn(List<ChessCell> cells, ChessPiece thePiece) {
		for (ChessCell chessCell : cells) {
			if ( chessCell.getPiece() != null && !(chessCell.getPiece() == thePiece))
				return true;
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
		return XMLConstants.TAG_ROOK;
	}

}