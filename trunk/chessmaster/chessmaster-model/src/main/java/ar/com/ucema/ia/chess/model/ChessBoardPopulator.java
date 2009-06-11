package ar.com.ucema.ia.chess.model;

import java.util.List;

import ar.com.ucema.ia.chess.model.pieces.Bishop;
import ar.com.ucema.ia.chess.model.pieces.King;
import ar.com.ucema.ia.chess.model.pieces.Knight;
import ar.com.ucema.ia.chess.model.pieces.Pawn;
import ar.com.ucema.ia.chess.model.pieces.Queen;
import ar.com.ucema.ia.chess.model.pieces.Rook;


/**
 * It populates the board with all the pieces
 * All white pieces are supposed to be in rows both 1 and 2 at the match start and black pieces at rows 7 and 8 
 * @author Matías Suárez
 */
public class ChessBoardPopulator {

	
	/**
	 * 
	 * @param board
	 */
	public void populateBoard(ChessBoard board) {
		createOtherPiecesFromColorToEntireRown(Color.White, board.getChessCellRow(1));
		createOtherPiecesFromColorToEntireRown(Color.Black, board.getChessCellRow(8));
		createPawnsFromColorToEntireRown(Color.White, board.getChessCellRow(2));
		createPawnsFromColorToEntireRown(Color.Black, board.getChessCellRow(7));
	}
	
	private void createPawnsFromColorToEntireRown(Color color, List<ChessCell> entireRow) {
		for (ChessCell aCell : entireRow) {
			aCell.setPiece(new Pawn(color));
		}
	}
	
	private void createOtherPiecesFromColorToEntireRown(Color color, List<ChessCell> entireRow) {
		for (ChessCell aCell : entireRow) {
			if ( aCell.getColumn().equals(ChessColumns.A) || aCell.getColumn().equals(ChessColumns.H))
				aCell.setPiece(new Rook(color));

			if ( aCell.getColumn().equals(ChessColumns.B) || aCell.getColumn().equals(ChessColumns.G))
				aCell.setPiece(new Knight(color));

			if ( aCell.getColumn().equals(ChessColumns.C) || aCell.getColumn().equals(ChessColumns.F))
				aCell.setPiece(new Bishop(color));

			if ( aCell.getColumn().equals(ChessColumns.D) )
				aCell.setPiece(new King(color));

			if ( aCell.getColumn().equals(ChessColumns.E) )
				aCell.setPiece(new Queen(color));
		}
	}
}