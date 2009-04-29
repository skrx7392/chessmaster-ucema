package ar.com.ucema.ia.chess.xml;

import junit.framework.Test;
import junit.framework.TestSuite;
import ar.com.ucema.ia.chess.xml.fixtures.BishopParsingTestCase;
import ar.com.ucema.ia.chess.xml.fixtures.ChessBoardParsingTestCase;
import ar.com.ucema.ia.chess.xml.fixtures.ChessCellParsingTestCase;
import ar.com.ucema.ia.chess.xml.fixtures.KingParsingTestCase;
import ar.com.ucema.ia.chess.xml.fixtures.KnightParsingTestCase;
import ar.com.ucema.ia.chess.xml.fixtures.PawnParsingTestCase;
import ar.com.ucema.ia.chess.xml.fixtures.QueenParsingTestCase;
import ar.com.ucema.ia.chess.xml.fixtures.RookParsingTestCase;

public class XMLParsingTestSuite {

	public static Test suite() {
	    TestSuite xmlTestSuite = new TestSuite();
	    xmlTestSuite.addTestSuite(BishopParsingTestCase.class);
	    xmlTestSuite.addTestSuite(KingParsingTestCase.class);
	    xmlTestSuite.addTestSuite(KnightParsingTestCase.class);
	    xmlTestSuite.addTestSuite(PawnParsingTestCase.class);
	    xmlTestSuite.addTestSuite(QueenParsingTestCase.class);
	    xmlTestSuite.addTestSuite(RookParsingTestCase.class);
	    xmlTestSuite.addTestSuite(ChessCellParsingTestCase.class);
	    xmlTestSuite.addTestSuite(ChessBoardParsingTestCase.class);
	    return xmlTestSuite;
	}
}
