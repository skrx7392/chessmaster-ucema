
public class Pawn extends ChessPiece {

	public Pawn(Color color) {
		super(color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return this.getColor() + " Pawn";
	}

	@Override
	public boolean eat(ChessCell to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidMove(ChessCell from, ChessCell to) {
		// TODO Auto-generated method stub
		return false;
	}
}
