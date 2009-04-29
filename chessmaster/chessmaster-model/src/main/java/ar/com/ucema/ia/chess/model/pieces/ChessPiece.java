package ar.com.ucema.ia.chess.model.pieces;

import org.dom4j.Element;

import ar.com.ucema.ia.chess.model.ChessBoard;
import ar.com.ucema.ia.chess.model.ChessCell;
import ar.com.ucema.ia.chess.model.Color;
import ar.com.ucema.ia.chess.model.Parseable;
import ar.com.ucema.ia.chess.model.xml.XMLConstants;

/**
 * Defines the basic behavior for all chess pieces.
 * 
 * @author ejadib
 */
public abstract class ChessPiece implements Parseable {
	private Color color;
	private Integer id;
	
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
	
	/**
	 * Returns the piece xml tag.
	 * @return
	 */
	public abstract String getPieceNameXMLTag();

	public Element toXML(Element root) {
		Element e = root.addElement(getPieceNameXMLTag()).addAttribute(XMLConstants.ATTRIBUTE_COLOR, getColor().toString());
		return e;
	}
	
	public Object fromXML(Element e) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Color getColor() {
		return color;
	}

	private void setColor(Color color) {
		this.color = color;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		} else if ( (!color.equals(other.color)) && (getPieceNameXMLTag().equals(other.getPieceNameXMLTag())) )
			return false;
		return true;
	}
	
}