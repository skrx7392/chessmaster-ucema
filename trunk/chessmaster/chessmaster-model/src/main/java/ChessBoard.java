import java.util.ArrayList;

/**
 * Defines the chess board.
 * @author ejadib
 */
public class ChessBoard {

	private ArrayList<ChessCell> cells;
	
	public ChessBoard() {
		this.initializeBoard();
	}

	private void initializeBoard() {
		this.cells = new ArrayList<ChessCell>();

		String[] columns = new String[]{"a", "b", "c", "d", "e", "f", "g", "h" };

		for (String column : columns) {
			for (int row = 0; row < 7; row++) {
				this.cells.add(new ChessCell(column, row + 1));
				
			}
		}
		
		this.cells.get(0).setPiece(new Pawn(Color.White));
		this.cells.get(1).setPiece(new Pawn(Color.White));
		
		this.dumpBoard();
	}
	
	private void dumpBoard() {
		for (ChessCell cell : this.cells) {
			System.out.println(cell.toString());
		}
	}
}
