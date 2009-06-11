package ar.com.ucema.ia.chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import ar.com.ucema.ia.chess.model.ChessBoard;
import ar.com.ucema.ia.chess.model.ChessCell;
import ar.com.ucema.ia.chess.model.ChessMovement;
import ar.com.ucema.ia.chess.model.Color;
import ar.com.ucema.ia.chess.model.Parseable;
import ar.com.ucema.ia.chess.model.xml.XMLConstants;

/**
 * Defines the basic behavior for all chess pieces.
 * 
 * @author ejadib
 */
public abstract class ChessPiece implements Parseable {
	private static Long generetor = 0L;
	private Color color;
	private Long id;
	
	public ChessPiece(Color color) {
		this.setColor(color);
		this.id = generetor;
		
		// incrementa el generador.
		ChessPiece.generetor++;
	}

	/**
	 * Checks if the movement the piece is trying to do is valid. At this moment, the only check that is made is to see if the king is in check. 
	 * @param move the move of the play.
	 * @return true if the movement is valid acording to chess movement rules, false otherwise.
	 */
	public boolean isValidMove(ChessMovement move) {
		if (move.leavesKingInCheck(move))
			return false;
		
		// verifico que la posicion a donde quiere ir, no haya una pieza del mismo color
		if ( canEat(move) )
			if (move.getBoard().getChessCellAt(move.getFrom()).getPiece().getColor().equals(move.getBoard().getChessCellAt(move.getTo()).getPiece().getColor()))
				return false;
		
		return true;
	}
	
	/**
	 * Eats the pieces right out of the board.
	 * @param move the piece that is about to eat.
	 */
	public boolean move(ChessMovement move) {
		if ( canEat(move) ) {
			move.getBoard().getChessCellAt(move.getFrom()).setPiece(null);
			move.getBoard().getChessCellAt(move.getTo()).setPiece(this);
			return true;
		}
		
		return false;

	}

	protected boolean canEat(ChessMovement move) {
		return move.getBoard().getChessCellAt(move.getTo()).getPiece() != null;
	}

	
	public List<ChessMovement> getPossibleMovements(ChessCell currentCell) {
		if ( this.color.equals(Color.Black) )
			return getBlackPossibleMovements(currentCell);
		else
			return getWhitePossibleMovements(currentCell);
	}

	/**
	 * Intended to be overriden
	 * @param currentCell the current cell
	 */
	public List<ChessMovement> getWhitePossibleMovements(ChessCell currentCell) {
		return new ArrayList<ChessMovement>();
	}
	

	/**
	 * Intended to be overriden
	 * @param currentCell the current cell
	 */
	public List<ChessMovement> getBlackPossibleMovements(ChessCell currentCell) {
		return new ArrayList<ChessMovement>();
	}
	
	
	/**
	 * Checks if the piece can do any movement within the board.
	 * @param board the chess board.
	 * @return true if the piece can do at least one posible movement, false otherwise.
	 */
	public abstract boolean canMove(ChessBoard board);
	
	
	/**
	 * Obtiene el valor de una pieza para el juego
	 * 
	 * @return
	 */
	public abstract Integer getPieceValue();
	
	
	/**
	 * Returns the piece xml tag.
	 * @return
	 */
	public abstract String getPieceNameXMLTag();

	public Element toXML(Element root) {
		Element e = root.addAttribute(XMLConstants.ATTRIBUTE_TYPE, getPieceNameXMLTag()).addAttribute(XMLConstants.ATTRIBUTE_COLOR, getColor().toString());
		return e;
	}
	
	public Color getColor() {
		return color;
	}

	protected void setColor(Color color) {
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ChessPiece))
			return false;
		ChessPiece other = (ChessPiece) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if ( (!color.equals(other.color)) && (getPieceNameXMLTag().equals(other.getPieceNameXMLTag())) && (id.equals(other.id)))
			return false;
		return true;
	}
	
}