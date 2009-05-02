package ar.com.ucema.ia.chess.model.pieces;

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
	
	@Override
	public String toString() {
		return this.getColor() + " Pawn";
	}

	@Override
	public boolean eat(ChessCell to) {
		return false;
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
				
				// sino pasó la mitad del tablero, puede llegar hasta la mitad.
				if (destinyRow >= 5 ) {
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
	 * @param move TODO
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
				
				// sino pasó la mitad del tablero, puede llegar hasta la mitad.
				if (destinyRow <= 4 ) {
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

}