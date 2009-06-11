package ar.com.ucema.ia.chess.stuff;

import junit.framework.Test;
import junit.framework.TestSuite;
import ar.com.ucema.ia.chess.stuff.fixtures.ChessColumnsTestCase;

public class StuffTestSuite {

	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    suite.addTestSuite(ChessColumnsTestCase.class);
	    return suite;
	}
}
