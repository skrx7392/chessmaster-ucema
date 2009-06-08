package ar.com.ucema.ia.chess.web.services;

import org.dom4j.Document;

import ar.com.ucema.ia.chess.model.ChessPlay;

/**
 *
 * @author matiassuarez
 *
 */
public interface ChessXMLService {

	public Document playWhite(ChessPlay play);
	
	
	public Document playBlack(ChessPlay play);
	
}
