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
		
		list.addAll(getDiagonalMovements(currentCell));

		return list;
	}

	protected List<ChessMovement> getDiagonalMovements(ChessCell currentCell) {
		List<ChessMovement> list = new ArrayList<ChessMovement>();
		ChessColumns columns = new ChessColumns();
		int columnNumber = columns.getColumnNumber(currentCell.getColumn()); 
		int positiveRowMoves = 1;
		int negativeRowMoves = -1;
		
		for (int i = (columnNumber+1); i <= ChessBoard.MAX_ROWS; i++) {
			// verifico que no se vaya de rango.
			if ( (currentCell.getRow() + positiveRowMoves) <= ChessBoard.MAX_ROWS)
				list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(), positiveRowMoves), currentCell.getRow() + positiveRowMoves, this)));
			
			if ( (currentCell.getRow() - positiveRowMoves) > 0)
				list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(), positiveRowMoves), currentCell.getRow() - positiveRowMoves, this)));
			positiveRowMoves++;
		}
		
		for (int i = (columnNumber-1); i > 0; i--) {
			// verifico que no se vaya de rango.
			if ( (currentCell.getRow() + negativeRowMoves) > 0)  
				list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(), negativeRowMoves), currentCell.getRow() + negativeRowMoves, this)));
			
			if ( (currentCell.getRow() - negativeRowMoves) <= ChessBoard.MAX_ROWS)
				list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(), negativeRowMoves), currentCell.getRow() - negativeRowMoves, this)));
			
			negativeRowMoves--;
		}
		
		return list;
	}

}