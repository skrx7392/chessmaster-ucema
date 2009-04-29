package ar.com.ucema.ia.chess.model.pieces;

import ar.com.ucema.ia.chess.model.ChessBoard;
import ar.com.ucema.ia.chess.model.ChessCell;
import ar.com.ucema.ia.chess.model.Color;


/**
 *  
 * @author Matías Suárez
 */
public class Pawn extends ChessPiece {

	public Pawn(Color color) {
		super(color);
	}
	
	@Override
	public String toString() {
		return this.getColor() + " Pawn";
	}

	@Override
	public boolean eat(ChessCell to) {
		return false;
	}

	@Override
	public boolean isValidMove(ChessCell from, ChessCell to) {
		return false;
	}

	@Override
	public boolean canMove(ChessBoard board) {
		return false;
	}
}
