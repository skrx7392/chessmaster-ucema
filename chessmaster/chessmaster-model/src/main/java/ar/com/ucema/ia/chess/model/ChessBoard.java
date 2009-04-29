package ar.com.ucema.ia.chess.model;

import java.util.ArrayList;
import java.util.List;

import ar.com.ucema.ia.chess.model.exceptions.ChessCellNonExistentException;
import ar.com.ucema.ia.chess.model.pieces.ChessPiece;
import ar.com.ucema.ia.chess.model.pieces.Pawn;

/**
 * Defines the chess board.
 * 
 * @author ejadib
 */
public class ChessBoard {
	private List<ChessCell> cells;

	public ChessBoard() {
		this.initializeBoard();
	}

	private void initializeBoard() {
		this.cells = new ArrayList<ChessCell>();

		String[] columns = new String[] { "a", "b", "c", "d", "e", "f", "g", "h" };

		for (String column : columns) {
			for (int row = 0; row < 7; row++) {
				this.cells.add(new ChessCell(column, row + 1));
			}
		}

		this.cells.get(0).setPiece(new Pawn(Color.White));
		this.cells.get(1).setPiece(new Pawn(Color.White));

		this.dumpBoard();
	}

	private void dumpBoard() {
		for (ChessCell cell : this.cells) {
			System.out.println(cell.toString());
		}
	}
	
	
	/**
	 * Gets the chess cell from the board at a given position
	 */
	public ChessCell getChessCellAt(String column, Integer row) {
		ChessCell aPosibleCell = new ChessCell(column,row);
		
		if ( cells.contains(aPosibleCell) ) {
			return cells.get(cells.indexOf(aPosibleCell));
		} else
			throw new ChessCellNonExistentException("There is no ChessCell given that position. " + aPosibleCell.toString());
	}

	/**
	 * Gets the chess piece from the board at a given position 
	 */
	public ChessPiece getPieceAt(String column, Integer row) {
		ChessCell cell = getChessCellAt(column, row);
		
		if ( cell.getPiece() != null ) {
			return cell.getPiece();
		} else
			throw new ChessCellNonExistentException("There is no ChessPiece given that position. " + cell.toString());
	}
	
	/**
	 * Removes the piece from the board 
	 */
	public void removePieceAt(String column, Integer row) {
		ChessCell cell = getChessCellAt(column, row);
		cell.setPiece(null);
	}
}