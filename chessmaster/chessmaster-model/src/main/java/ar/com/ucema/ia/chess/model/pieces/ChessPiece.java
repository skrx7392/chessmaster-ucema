package ar.com.ucema.ia.chess.model.pieces;

import ar.com.ucema.ia.chess.model.ChessBoard;
import ar.com.ucema.ia.chess.model.ChessCell;
import ar.com.ucema.ia.chess.model.Color;

/**
 * Defines the basic behavior for all chess pieces.
 * 
 * @author ejadib
 */
public abstract class ChessPiece {
	private Color color;
	
	public ChessPiece(Color color) {
		this.setColor(color);
	}

	/**
	 * Checks if the movement the piece is trying to do es valid. 
	 * @param from the origin {@link ChessCell}
	 * @param to the {@link ChessBoard} destination.
	 * @return true if the movement is valid acording to chess movement rules, false otherwise.
	 */
	public abstract boolean isValidMove(ChessCell from, ChessCell to);
	
	/**
	 * Eats the pieces right out of the board.
	 * @param to the piece that is about to eat.
	 */
	public abstract boolean eat(ChessCell to);

	/**
	 * Checks if the piece can do any movement within the board.
	 * @param board the chess board.
	 * @return true if the piece can do at least one posible movement, false otherwise.
	 */
	public abstract boolean canMove(ChessBoard board);
	
	public Color getColor() {
		return color;
	}

	private void setColor(Color color) {
		this.color = color;
	}
}
