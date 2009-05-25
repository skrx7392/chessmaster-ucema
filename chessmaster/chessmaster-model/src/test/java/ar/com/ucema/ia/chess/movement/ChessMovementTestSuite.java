package ar.com.ucema.ia.chess.movement;

import junit.framework.Test;
import junit.framework.TestSuite;
import ar.com.ucema.ia.chess.movement.fixtures.KingMovementTestCase;
import ar.com.ucema.ia.chess.movement.fixtures.PawnMovementTestCase;
import ar.com.ucema.ia.chess.movement.fixtures.QueenMovementTestCase;
import ar.com.ucema.ia.chess.movement.fixtures.RookMovementTestCase;

public class ChessMovementTestSuite {

	public static Test suite() {
	    TestSuite suite = new TestSuite();

	    suite.addTestSuite(PawnMovementTestCase.class);
	    suite.addTestSuite(KingMovementTestCase.class);
	    suite.addTestSuite(QueenMovementTestCase.class);
	    suite.addTestSuite(RookMovementTestCase.class);
//	    suite.addTestSuite(PawnMovementTestCase.class);
//	    suite.addTestSuite(PawnMovementTestCase.class);
	    
	    return suite;
	}
}
