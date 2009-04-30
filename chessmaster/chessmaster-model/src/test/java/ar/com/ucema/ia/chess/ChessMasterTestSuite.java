package ar.com.ucema.ia.chess;

import junit.framework.Test;
import junit.framework.TestSuite;
import ar.com.ucema.ia.chess.movement.ChessMovementTestSuite;
import ar.com.ucema.ia.chess.stuff.StuffTestSuite;
import ar.com.ucema.ia.chess.xml.XMLParsingTestSuite;

public class ChessMasterTestSuite {

	
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    suite.addTest(XMLParsingTestSuite.suite());
	    //suite.addTest(ChessMovementTestSuite.suite());
	    suite.addTest(StuffTestSuite.suite());
	    return suite;
	}
}
