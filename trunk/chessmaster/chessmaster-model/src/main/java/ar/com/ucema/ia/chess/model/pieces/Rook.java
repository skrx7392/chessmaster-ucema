package ar.com.ucema.ia.chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import ar.com.ucema.ia.chess.model.BlackPieceChessMovement;
import ar.com.ucema.ia.chess.model.ChessBoard;
import ar.com.ucema.ia.chess.model.ChessCell;
import ar.com.ucema.ia.chess.model.ChessColumns;
import ar.com.ucema.ia.chess.model.ChessMovement;
import ar.com.ucema.ia.chess.model.Color;
import ar.com.ucema.ia.chess.model.xml.XMLConstants;

public class Rook extends ChessPiece {

	public Rook(Color color) {
		super(color);
	}

	public static Rook createBlackRook() {
		return new Rook(Color.Black);
	}

	public static Rook createWhiteRook() {
		return new Rook(Color.Black);
	}

	@Override
	public String toString() {
		return this.getColor() + " Rook";
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

		// verifico que no se quiera mover en forma diagonal.
		if ( movedByColumn(move) && movedByRow(move) )
			return false;
		
		//analizo el movimiento por columna
		if (movedByColumn(move) || (movedByRow(move) && move.getTo().getRow() <= 7)) {
			// si no hay ninguna pieza en el medio, es un movimiento valido.
			boolean isThereAnyPieceInBetween = move.getBoard().isThereAnyChessPieceIn(move.getBoard().getChessCellInBetween(move.getFrom(), move.getTo()), move.getBoard().getChessCellAt(move.getFrom()).getPiece()); 
			
			if ( isThereAnyPieceInBetween && move.getBoard().getChessCellAt(move.getTo()).getPiece() != null ) {
				return true;
			}
			
			return !isThereAnyPieceInBetween;
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

	@Override
	public Integer getPieceValue() {
		return 10;
	}

	@Override
	public List<ChessMovement> getBlackPossibleMovements(ChessCell currentCell) {
		List<ChessMovement> list = new ArrayList<ChessMovement>();
		ChessColumns columns = new ChessColumns();

		// movimientos horizontales
		for (int i = 1; i <= ChessBoard.MAX_ROWS; i++) {
			int delta = i - currentCell.getRow();
			if ( delta != 0 )
				list.add(new BlackPieceChessMovement(currentCell, new ChessCell(currentCell.getColumn(), currentCell.getRow() + delta, this)));
		}
		
		// movimientos verticales
		for (int i = 1; i <= ChessBoard.MAX_COLS; i++) {
			int delta = i - columns.getColumnNumber(currentCell.getColumn());
			if ( delta != 0 )
				list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(), delta), currentCell.getRow(), this)));
		}

		return list;
	}

}