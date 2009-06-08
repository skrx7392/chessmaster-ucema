package ar.com.ucema.ia.chess.web.services.impl;

import org.dom4j.Document;

import ar.com.ucema.ia.chess.model.ChessGame;
import ar.com.ucema.ia.chess.model.ChessPlay;
import ar.com.ucema.ia.chess.web.services.ChessXMLService;

/**
 * Implementacion de {@link ChessXMLService}
 * 
 * @author matiassuarez
 */
public class ChessXMLServiceImpl implements ChessXMLService {


	public Document playWhite(ChessPlay aPlay) {
		ChessGame game = ChessGame.getInstance();
		game.playWhite(aPlay);
		return game.toXML();
	}

	public Document playBlack(ChessPlay play) {
		ChessGame game = ChessGame.getInstance();
		game.playBlack();
		return game.toXML();
	}
	

}
