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
public class Pawn extends ChessPiece {

	public Pawn(Color color) {
		super(color);
	}
	
	public static Pawn createBlackPawn() {
		return new Pawn(Color.Black);
	}

	public static Pawn createWhitePawn() {
		return new Pawn(Color.Black);
	}
	
	@Override
	public String toString() {
		return this.getColor() + " Pawn";
	}

	@Override
	public boolean isValidMove(ChessMovement move) {
		// siempre se tiene que llamar debido a que hace la validacion de rey en jaque.
		if ( super.isValidMove(move) == false )
			return false;
		
		if ( getColor().equals(Color.White)) {
			return validateWhiteMovement(move);
		} else {
			return validateBlackMovement(move);
		}
	}

	/**
	 * valida el movimiento de un peon negro.
	 * @param destinyRow la posicion del row a la que se quiere mover.
	 * @param currentRow la posicion actual del row del peon.
	 * @return true si se puede mover, false en caso contrario.
	 */
	private boolean validateBlackMovement(ChessMovement move) {
		int destinyRow = move.getTo().getRow();
		int currentRow = move.getFrom().getRow();
		ChessColumns columns = new ChessColumns();
		
		// verifica que esten en la misma columna.
		if ( move.getFrom().getColumn().equals(move.getTo().getColumn())) {
			
			// verifica que se quiera mover para adelante.
			if (destinyRow < currentRow) {
				// falta verificar que la posicion a la que se quiere mover no este ocupada por otra pieza.
				if ( move.getBoard().getChessCellAt(move.getFrom().getColumn(), destinyRow).getPiece() != null)
					return false;

				// sino pasó la mitad del tablero, puede llegar hasta la mitad.
				if (destinyRow >= 5 ) {
					// tengo que verificar que no haya una pieza intermedia, solo puede estar en la posicion 6.
					// tengo que verificar que no haya una pieza intermedia, solo puede estar en la posicion 3.
					if ( move.getBoard().getChessCellAt(move.getFrom().getColumn(), 6).getPiece() != null ) {
						ChessPiece sourcePiece = move.getBoard().getChessCellAt(move.getFrom()).getPiece();
						ChessPiece otherPiece = move.getBoard().getChessCellAt(move.getFrom().getColumn(), 6).getPiece();
						
						// si es la misma pieza, es valido porque de hecho no esta queriendo hacer un movimiento.
						return (sourcePiece == otherPiece);
					} else
						return true;
				} else {
					return  ((destinyRow - currentRow) == -1); // si esta en la mitad del tablero, solo se puede mover de a una posicion.
				}
			}
		} else
			// verifica si quiere comer (si o si tiene que se una posicion para la derecha o para la izquierda.
			if ( (columns.addPositionToColumn(move.getFrom().getColumn(), 1).equals(move.getTo().getColumn())) || (columns.addPositionToColumn(move.getFrom().getColumn(), -1).equals(move.getTo().getColumn()))) {
				return (( destinyRow - currentRow) == 1);
			}
		
		return false;
	}

	/**
	 * valida el movimiento de un peon blanco.
	 * @return true si se puede mover, false en caso contrario.
	 */
	private boolean validateWhiteMovement(ChessMovement move) {
		int destinyRow = move.getTo().getRow();
		int currentRow = move.getFrom().getRow();
		
		ChessColumns columns = new ChessColumns();
		
		// verifica que esten en la misma columna.
		if ( move.getFrom().getColumn().equals(move.getTo().getColumn())) {
			// verifica que se quiera mover para adelante.
			if (destinyRow > currentRow) {
				// falta verificar que la posicion a la que se quiere mover no este ocupada por otra pieza.
				if ( move.getBoard().getChessCellAt(move.getFrom().getColumn(), destinyRow).getPiece() != null)
					return false;
				
				// sino pasó la mitad del tablero, puede llegar hasta la mitad.
				if (destinyRow <= 4 ) {
					// tengo que verificar que no haya una pieza intermedia, solo puede estar en la posicion 3.
					if ( move.getBoard().getChessCellAt(move.getFrom().getColumn(), 3).getPiece() != null ) {
						ChessPiece sourcePiece = move.getBoard().getChessCellAt(move.getFrom()).getPiece();
						ChessPiece otherPiece = move.getBoard().getChessCellAt(move.getFrom().getColumn(), 3).getPiece();
						
						// si es la misma pieza, es valido porque de hecho no esta queriendo hacer un movimiento.
						return (sourcePiece == otherPiece);
					} else
						return true;
				} else {
					return  ((destinyRow - currentRow) == 1); // si esta en la mitad del tablero, solo se puede mover de a una posicion.
				}
			}
		} else
			// verifica si quiere comer (si o si tiene que se una posicion para la derecha o para la izquierda.
			if ( (columns.addPositionToColumn(move.getFrom().getColumn(), 1).equals(move.getTo().getColumn())) || (columns.addPositionToColumn(move.getFrom().getColumn(), -1).equals(move.getTo().getColumn()))) {
				return (( destinyRow - currentRow) == 1);
			}
		return false;
	}

	@Override
	public boolean canMove(ChessBoard board) {
		return false;
	}

	@Override
	public String getPieceNameXMLTag() {
		return XMLConstants.TAG_PAWN;
	}

	@Override
	public Integer getPieceValue() {
		return 1;
	}

	@Override
	public List<ChessMovement> getBlackPossibleMovements(ChessCell currentCell) {
		List<ChessMovement> list = new ArrayList<ChessMovement>();
		ChessColumns columns = new ChessColumns();
		
		if ( (currentCell.getRow() - 1) > 0 ) {
			list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(), -1), currentCell.getRow() - 1, this)));
			list.add(new BlackPieceChessMovement(currentCell, new ChessCell(columns.addPositionToColumn(currentCell.getColumn(), 1), currentCell.getRow() - 1, this)));
			list.add(new BlackPieceChessMovement(currentCell, new ChessCell(currentCell.getColumn(), currentCell.getRow() - 1, this)));
		}
		
		if ( (currentCell.getRow() - 2) > 0 ) 
			list.add(new BlackPieceChessMovement(currentCell, new ChessCell(currentCell.getColumn(), currentCell.getRow() - 2, this)));
		
		return list;
	}

}