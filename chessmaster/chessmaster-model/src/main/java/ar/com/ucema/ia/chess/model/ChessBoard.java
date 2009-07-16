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
 * @author Matías Suárez.
 */
public class ChessBoard implements Parseable, Cloneable {
	public static final int MAX_ROWS = 8;
	public static final int MAX_COLS = 8;
	private List<ChessCell> cells;

	public ChessBoard() {
		ChessBoardPopulator populator = new ChessBoardPopulator();
		
		this.initializeBoard();
		populator.populateBoard(this);
	}

	public ChessBoard(Boolean isEmpty) {
		this.initializeBoard();

		if (!isEmpty) {
			ChessBoardPopulator populator = new ChessBoardPopulator();
			populator.populateBoard(this);
		}
	}

	/**
	 * verificar si el rey del determinado color esta en jaque o no.
	 * @param color
	 * @return
	 */
	public boolean isKingInCheck(Color color) {
		return true;
	}
	
	private void initializeBoard() {
		this.cells = new ArrayList<ChessCell>();
		
		ChessColumns chessColumns = new ChessColumns();
		List<String> columns = chessColumns.getColumns();

		for (String column : columns) {
			for (int row = 0; row < 8; row++) {
				this.cells.add(new ChessCell(column, row + 1));
			}
		}
	}

	/**
	 * Obtiene todas las celdas de entre 2 celdas posibles. Las celdas deben estas separadas horizontalmente, verticalmente o diagonal.
	 * @param aCell
	 * @param anotherCell
	 * @return
	 */
	public List<ChessCell> getChessCellInBetween(ChessCell aCell, ChessCell anotherCell) {
		if ( aCell.getColumn().equals(anotherCell.getColumn()) )
			return getChessCellInBetweenArrangedVertically(aCell, anotherCell);
		else
			if ( aCell.getRow().equals(anotherCell.getRow()) )
				return getChessCellInBetweenArrangedHorizontally(aCell, anotherCell);
			else
				return getChessCellInBetweenArrangedDiagonally(aCell, anotherCell);
	}
	
	
	private List<ChessCell> getChessCellInBetweenArrangedDiagonally(ChessCell cell, ChessCell anotherCell) {
		List<ChessCell> list = new ArrayList<ChessCell>();
		ChessColumns columns = new ChessColumns();

		int startIndex = (cell.getRow() > anotherCell.getRow()) ? anotherCell.getRow() : cell.getRow();
		int endIndex = (cell.getRow() > anotherCell.getRow()) ? cell.getRow() : anotherCell.getRow();
		

		for (int i = startIndex; i <= endIndex; i++) {
			// problema: no se como identificar si columna la tengo que sumar o restar.
			int collPositions = i - startIndex;
			int rowPositions = i - startIndex;
			
			if ( columns.getColumnNumber(cell.getColumn()) > columns.getColumnNumber(anotherCell.getColumn()) )
				collPositions *= -1;
			
			if ( cell.getRow() > anotherCell.getRow() )
				rowPositions *= -1;
			
			list.add(getChessCellAt(columns.addPositionToColumn(cell.getColumn(), collPositions), cell.getRow() + rowPositions));
		}

		return list;
	}

	private List<ChessCell> getChessCellInBetweenArrangedHorizontally(ChessCell cell, ChessCell anotherCell) {
		List<ChessCell> list = new ArrayList<ChessCell>();
		ChessColumns columns = new ChessColumns();
		int startIndex = (columns.getColumnNumber(cell.getColumn()) > columns.getColumnNumber(anotherCell.getColumn())) ? columns.getColumnNumber(anotherCell.getColumn()) : columns.getColumnNumber(cell.getColumn());
		int endIndex =   (columns.getColumnNumber(cell.getColumn()) > columns.getColumnNumber(anotherCell.getColumn())) ? columns.getColumnNumber(cell.getColumn()) : columns.getColumnNumber(anotherCell.getColumn());

		for (int i = startIndex; i <= endIndex; i++) {
			list.add(getChessCellAt(columns.getColumnByNumber(i), cell.getRow()));
		} 
		return list;
	}

	private List<ChessCell> getChessCellInBetweenArrangedVertically(ChessCell cell, ChessCell anotherCell) {
		List<ChessCell> list = new ArrayList<ChessCell>();
		int startIndex = (cell.getRow() > anotherCell.getRow()) ? anotherCell.getRow() : cell.getRow();
		int endIndex = (cell.getRow() > anotherCell.getRow()) ? cell.getRow() : anotherCell.getRow();
		
		for (int i = startIndex; i <= endIndex; i++) {
			list.add(getChessCellAt(cell.getColumn(), i));
		}
		
		return list;
	}

	/**
	 * Checks out if there is any piece in the cells.
	 * @param cells all the chess posible cells.
	 * @param thePiece the piece in which the movement was made.
	 * @return true if there is a chess piece between the source and the destiny, false otherwise.
	 */
	public Boolean isThereAnyChessPieceIn(List<ChessCell> cells, ChessPiece thePiece) {
		for (ChessCell chessCell : cells) {
			if ( chessCell.getPiece() != null && !(chessCell.getPiece() == thePiece))
				return true;
		}
		return false;
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
	
	public ChessCell getChessCellAt(ChessCell cell) {
		if (cells.contains(cell)) {
			return cells.get(cells.indexOf(cell));
		} else
			throw new ChessCellNonExistentException("There is no ChessCell given that position. " + cell.toString());
	}

	/**
	 * Gets the chess cell from the board at a given position
	 */
	public List<ChessCell> getChessCellRow(Integer row) {
		List<ChessCell> list = new ArrayList<ChessCell>();

		for (ChessCell aCell : cells) {
			if (aCell.getRow().equals(row)) {
				list.add(aCell);
			}
		}
		return list;
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
	 * Sets the chess piece from the board at a given position
	 */
	public void setPieceAt(String column, Integer row, ChessPiece piece) {
		ChessCell cell = getChessCellAt(column, row);
		cell.setPiece(piece);
	}
	
	/**
	 * Removes the piece from the board
	 */
	public void removePieceAt(String column, Integer row) {
		ChessCell cell = getChessCellAt(column, row);
		cell.setPiece(null);
	}

	public Element toXML(Element root) {
		Element e = root.addElement(XMLConstants.TAG_CHESS_BOARD);

		for (ChessCell aCell : cells) {
			aCell.toXML(e);
		}

		return e;
	}
	
	public ChessBoard clone() {
		ChessBoard copiedBoard = new ChessBoard(true); // crea un board vacio
		List<ChessCell> chessCells = this.getChessCellsWithPieces();
		
		// se q cada una de estos cells tiene una pieza
		for (ChessCell chessCell : chessCells) {
			copiedBoard.setPieceAt(chessCell.getColumn(), chessCell.getRow(), chessCell.getPiece());
		}
		
		return copiedBoard;
	}

	public List<ChessCell> getChessCellsWithPieces() {
		List<ChessCell> chessCells = new ArrayList<ChessCell>();

		for (ChessCell chessCell : this.cells) {
			if ( chessCell.getPiece() != null )
				chessCells.add(chessCell);
		}
		return chessCells;
	}

	public List<ChessCell> getChessCellsWithPiecesAndColor(Color color) {
		List<ChessCell> chessCells = new ArrayList<ChessCell>();

		for (ChessCell chessCell : this.cells) {
			if ( chessCell.getPiece() != null && chessCell.getPiece().getColor().equals(color) )
				chessCells.add(chessCell);
		}
		return chessCells;
	}

	public Boolean isValidMovement(ChessMovement move) {
		ChessPiece chessPiece = this.getChessCellAt(move.getFrom()).getPiece();
		return chessPiece.isValidMove(move);
	}
	
	public Boolean moveWithThis(ChessMovement move) {
		ChessPiece chessPiece = this.getChessCellAt(move.getFrom()).getPiece();
		
		if ( chessPiece != null ) {
			if ( chessPiece.isValidMove(move) ) {
				chessPiece.move(move);
				return true;
			}
		}
		
		// no intenta mover ninguna pieza
		return false;
	}

	public List<ChessCell> getChessCells() {
		return this.cells;
	}
	
	
	public List<ChessPiece> getChessPiecesWithColor(Color color) {
		List<ChessPiece> list = new ArrayList<ChessPiece>();
		for (ChessCell chessCell : this.cells) {
			if ( chessCell.getPiece() != null && chessCell.getPiece().getColor().equals(color))
				list.add(chessCell.getPiece());
		}
		return list;
	}


	@SuppressWarnings("unchecked")
	public List<BlackPieceChessMovement> getAllPosibleBlackPiecesMovements() {
		List<ChessCell> chessCells = getChessCellsWithPiecesAndColor(Color.Black);
		List<BlackPieceChessMovement> movements = new ArrayList<BlackPieceChessMovement>();
		
		for (ChessCell aCell : chessCells) {
			List blackPossibleMovements = aCell.getPiece().getBlackPossibleMovements(aCell);
			movements.addAll(blackPossibleMovements);
		}
		
		return movements;
	}
	
}