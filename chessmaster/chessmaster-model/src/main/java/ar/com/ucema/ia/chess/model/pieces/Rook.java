package ar.com.ucema.ia.chess.model.pieces;

import ar.com.ucema.ia.chess.model.ChessBoard;
import ar.com.ucema.ia.chess.model.ChessCell;
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
	public boolean isValidMove(ChessCell from, ChessCell to) {
		return false;
	}

	@Override
	public String getPieceNameXMLTag() {
		return XMLConstants.TAG_ROOK;
	}

}