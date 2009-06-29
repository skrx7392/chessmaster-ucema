package ar.com.ucema.ia.chess.model;

import ar.com.ucema.ia.chess.model.xml.XMLConstants;

/**
 * Enumerates the colors of a chess piece.
 * 
 * @author ejadib
 */
public enum Color {
	White (XMLConstants.COLOR_WHITE),
	Black (XMLConstants.COLOR_BLACK);
	
	private String colorAsString;
	
	private Color(String colorAsString) {
		this.colorAsString = colorAsString;
	}

	
	public String toString() {
		return colorAsString;
	};
	
	public Color negate() {
		if ( this.equals(Color.White) )
			return Color.Black;
		else
			return Color.White;
	}


	public String getColorAsString() {
		return colorAsString;
	}


	public void setColorAsString(String colorAsString) {
		this.colorAsString = colorAsString;
	}
	
	

}
