package ar.com.ucema.ia.chess.stuff.fixtures;

import junit.framework.TestCase;
import ar.com.ucema.ia.chess.model.ChessColumns;

public class ChessColumnsTestCase extends TestCase {
	
	public void testAddColumns() {
		ChessColumns columns = new ChessColumns();
		assertEquals(ChessColumns.B, columns.addPositionToColumn(ChessColumns.A, 1));
		assertEquals(ChessColumns.C, columns.addPositionToColumn(ChessColumns.A, 2));
	}

	public void testSubstractColumns() {
		ChessColumns columns = new ChessColumns();
		assertEquals(ChessColumns.A, columns.addPositionToColumn(ChessColumns.B, -1));
	}

	public void testAddColumnOutOfRange() {
		ChessColumns columns = new ChessColumns();
		
		assertEquals(ChessColumns.A, columns.addPositionToColumn(ChessColumns.A, -1));
		assertEquals(ChessColumns.H, columns.addPositionToColumn(ChessColumns.H, 1));
	}
}
