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

public class Knight extends ChessPiece {

	public Knight(Color color) {
		super(color);
	}

	public static Knight createBlackKnight() {
		return new Knight(Color.Black);
	}

	public static Knight createWhiteKnight() {
		return new Knight(Color.Black);
	}

	@Override
	public String toString() {
		return this.getColor() + " Knight";
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
		
		int hMovement = Math.abs(move.getFrom().getRow() - move.getTo().getRow());
		int vMovement = Math.abs(columns.getColumnNumber(move.getFrom().getColumn()) - columns.getColumnNumber(move.getTo().getColumn()));
		
		if ( ((hMovement == 1) && (vMovement == 2)) || ((hMovement == 2) && (vMovement == 1)) )
			return true;
		
		return false;
	}

	@Override
	public String getPieceNameXMLTag() {
		return XMLConstants.TAG_KNIGHT;
	}

	@Override
	public Integer getPieceValue() {
		return 5;
	}

	@Override
	public List<ChessMovement> getBlackPossibleMovements(ChessCell currentCell) {
		List<ChessMovement> list = new ArrayList<ChessMovement>();
		ChessColumns columns = new ChessColumns();
		
		if ( (currentCell.getRow() + 2) <= ChessBoard.MAX_ROWS ) {
			list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(),  1), currentCell.getRow() + 2, this)));
			list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(), -1), currentCell.getRow() + 2, this)));
		}

		if ( (currentCell.getRow() + 1) <= ChessBoard.MAX_ROWS ) {
			list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(),  2), currentCell.getRow() + 1, this)));
			list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(), -2), currentCell.getRow() + 1, this)));
		}
		
		if ( (currentCell.getRow() - 2) > 0 ) {
			list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(),  1), currentCell.getRow() - 2, this)));
			list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(), -1), currentCell.getRow() - 2, this)));
		}
		
		if ( (currentCell.getRow() - 1) > 0 ) {
			list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(),  2), currentCell.getRow() - 1, this)));
			list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(), -2), currentCell.getRow() - 1, this)));
		}

		return list;
	}

}