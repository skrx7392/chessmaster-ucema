package ar.com.ucema.ia.chess.model;

import ar.com.ucema.ia.chess.model.pieces.ChessPiece;

/**
 * Defines the cell of a chess board.
 * 
 * @author ejadib
 */
public class ChessCell {
	private String column;
	private Integer row;
	private ChessPiece piece;

	public ChessCell(String column, Integer row, ChessPiece piece) {
		this.setColumn(column);
		this.setRow(row);
		this.setPiece(piece);
	}

	public ChessCell(String column, Integer row) {
		this.setColumn(column);
		this.setRow(row);
	}

	public String getColumn() {
		return column;
	}

	private void setColumn(String column) {
		this.column = column;
	}

	public Integer getRow() {
		return row;
	}

	private void setRow(Integer row) {
		this.row = row;
	}

	public ChessPiece getPiece() {
		return piece;
	}

	public void setPiece(ChessPiece piece) {
		this.piece = piece;
	}

	@Override
	public String toString() {
		String pieceDescription = (piece != null) ? piece.toString() : "";
		return column + row + ":" + pieceDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((column == null) ? 0 : column.hashCode());
		result = prime * result + ((row == null) ? 0 : row.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ChessCell))
			return false;
		ChessCell other = (ChessCell) obj;
		if (column == null) {
			if (other.column != null)
				return false;
		} else if (!column.equals(other.column))
			return false;
		if (row == null) {
			if (other.row != null)
				return false;
		} else if (!row.equals(other.row))
			return false;
		return true;
	}
	
}
