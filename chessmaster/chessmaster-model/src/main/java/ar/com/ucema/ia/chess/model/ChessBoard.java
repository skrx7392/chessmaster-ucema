package ar.com.ucema.ia.chess.model;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import ar.com.ucema.ia.chess.model.exceptions.ChessCellNonExistentException;
import ar.com.ucema.ia.chess.model.pieces.ChessPiece;
import ar.com.ucema.ia.chess.model.xml.XMLConstants;

/**
 * Defines the chess board.
 * 
 * @author ejadib
 */
public class ChessBoard implements Parseable {
	private List<ChessCell> cells;

	public ChessBoard() {
		this.initializeBoard();
	}

	private void initializeBoard() {
		this.cells = new ArrayList<ChessCell>();
		
		ChessColumns chessColumns = new ChessColumns();
		List<String> columns = chessColumns.getColumns();

		for (String column : columns) {
			for (int row = 0; row < 7; row++) {
				this.cells.add(new ChessCell(column, row + 1));
			}
		}
	}

	/**
	 * Gets the chess cell from the board at a given position
	 */
	public ChessCell getChessCellAt(String column, Integer row) {
		ChessCell aPosibleCell = new ChessCell(column, row);

		if (cells.contains(aPosibleCell)) {
			return cells.get(cells.indexOf(aPosibleCell));
		} else
			throw new ChessCellNonExistentException("There is no ChessCell given that position. " + aPosibleCell.toString());
	}

	/**
	 * Gets the chess piece from the board at a given position
	 */
	public ChessPiece getPieceAt(String column, Integer row) {
		ChessCell cell = getChessCellAt(column, row);

		if (cell.getPiece() != null) {
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

	public Object fromXML(Element e) {
		// TODO Auto-generated method stub
		return null;
	}

	public Element toXML(Element root) {
		Element e = root.addElement(XMLConstants.TAG_CHESS_BOARD);

		for (ChessCell aCell : cells) {
			aCell.toXML(e);
		}

		return e;
	}
}