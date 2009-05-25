package ar.com.ucema.ia.chess.movement.fixtures;import junit.framework.TestCase;import ar.com.ucema.ia.chess.model.BlackPieceChessMovement;import ar.com.ucema.ia.chess.model.ChessBoard;import ar.com.ucema.ia.chess.model.ChessCell;import ar.com.ucema.ia.chess.model.ChessColumns;import ar.com.ucema.ia.chess.model.Color;import ar.com.ucema.ia.chess.model.WhitePieceChessMovement;import ar.com.ucema.ia.chess.model.pieces.Pawn;public class PawnMovementTestCase extends TestCase {		/**	 * Teste el movimiento valido de un peon blanco que se mueve una posicion adelante sin que ponga el rey en jaque.	 * 	 */	public void testValidWhitePawnMoveAheadWithNoKingCheck() {		ChessBoard board = new ChessBoard();		Pawn pawn = new Pawn(Color.White);		Pawn blackPawn = new Pawn(Color.Black);				board.setPieceAt(ChessColumns.A, 3, blackPawn);				assertTrue(pawn.isValidMove(new WhitePieceChessMovement(new ChessCell(ChessColumns.A, 3),new ChessCell(ChessColumns.A,4), board)));		assertFalse(pawn.isValidMove(new WhitePieceChessMovement(new ChessCell(ChessColumns.A, 2),new ChessCell(ChessColumns.A,3), board)));		assertTrue(pawn.isValidMove(new WhitePieceChessMovement(new ChessCell(ChessColumns.A, 3),new ChessCell(ChessColumns.A,4), board)));		assertTrue(pawn.isValidMove(new WhitePieceChessMovement(new ChessCell(ChessColumns.A, 4),new ChessCell(ChessColumns.A,5), board)));		assertFalse(pawn.isValidMove(new WhitePieceChessMovement(new ChessCell(ChessColumns.A, 4),new ChessCell(ChessColumns.A,6), board)));		assertFalse(pawn.isValidMove(new WhitePieceChessMovement(new ChessCell(ChessColumns.A, 4),new ChessCell(ChessColumns.A,3), board)));		assertFalse(pawn.isValidMove(new WhitePieceChessMovement(new ChessCell(ChessColumns.A, 4),new ChessCell(ChessColumns.A,4), board)));	}	/**	 * Teste el movimiento valido de un peon negro que se mueve una posicion adelante sin que ponga el rey en jaque.	 * 	 */	public void testValidBlackPawnMoveAheadWithNoKingCheck() {		ChessBoard board = new ChessBoard();		Pawn pawn = new Pawn(Color.Black);		assertTrue(pawn.isValidMove(new BlackPieceChessMovement(new ChessCell(ChessColumns.A, 7),new ChessCell(ChessColumns.A,6), board)));		assertTrue(pawn.isValidMove(new BlackPieceChessMovement(new ChessCell(ChessColumns.A, 7),new ChessCell(ChessColumns.A,5), board)));		assertTrue(pawn.isValidMove(new BlackPieceChessMovement(new ChessCell(ChessColumns.A, 6),new ChessCell(ChessColumns.A,5), board)));		assertTrue(pawn.isValidMove(new BlackPieceChessMovement(new ChessCell(ChessColumns.A, 5),new ChessCell(ChessColumns.A,4), board)));		assertFalse(pawn.isValidMove(new BlackPieceChessMovement(new ChessCell(ChessColumns.A, 5),new ChessCell(ChessColumns.A,3), board)));		assertFalse(pawn.isValidMove(new BlackPieceChessMovement(new ChessCell(ChessColumns.A, 5),new ChessCell(ChessColumns.A,6), board)));		assertFalse(pawn.isValidMove(new BlackPieceChessMovement(new ChessCell(ChessColumns.A, 5),new ChessCell(ChessColumns.A,5), board)));			}		/**	 * Teste el movimiento valido de un peon blanco que se mueve una posicion adelante sin que ponga el rey en jaque.	 * 	 */	public void testValidWhitePawnMoveForEatWithNoKingCheck() {		ChessBoard board = new ChessBoard(true);		Pawn whitePawn = new Pawn(Color.White);		Pawn blackPawn = new Pawn(Color.Black);		Pawn anotherBlackPawn = new Pawn(Color.Black);				board.setPieceAt(ChessColumns.B, 2, whitePawn);		board.setPieceAt(ChessColumns.A, 3, blackPawn);		board.setPieceAt(ChessColumns.C, 3, anotherBlackPawn);				assertTrue(whitePawn.isValidMove(new WhitePieceChessMovement(new ChessCell(ChessColumns.B, 2),new ChessCell(ChessColumns.A,3), board)));		assertFalse(whitePawn.isValidMove(new WhitePieceChessMovement(new ChessCell(ChessColumns.B, 2),new ChessCell(ChessColumns.A,4), board)));		assertTrue(whitePawn.isValidMove(new WhitePieceChessMovement(new ChessCell(ChessColumns.B, 2),new ChessCell(ChessColumns.C,3), board)));		assertFalse(whitePawn.isValidMove(new WhitePieceChessMovement(new ChessCell(ChessColumns.B, 2),new ChessCell(ChessColumns.C,4), board)));	}			/**	 * Teste el movimiento valido de un peon que se quiere mover de A2 a A4 pero hay una pieza en A3	 * 	 */	public void testValidWhitePawnMoveWithAnotherPieceBlockNoKingCheck() {		ChessBoard board = new ChessBoard(true);		Pawn whitePawn = new Pawn(Color.White);		Pawn blackPawn = new Pawn(Color.Black);				board.setPieceAt(ChessColumns.A, 2, whitePawn);		board.setPieceAt(ChessColumns.A, 3, blackPawn);				assertFalse(whitePawn.isValidMove(new WhitePieceChessMovement(new ChessCell(ChessColumns.A, 2),new ChessCell(ChessColumns.A,4), board)));	}}