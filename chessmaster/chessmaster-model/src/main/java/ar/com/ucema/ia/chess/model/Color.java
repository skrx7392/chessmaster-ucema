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

}
