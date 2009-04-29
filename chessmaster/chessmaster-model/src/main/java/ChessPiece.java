/**
 * Defines an interface for all the pieces
 * 
 * @author ejadib
 */
public abstract class ChessPiece {
	
	private Color color;
	
	public ChessPiece(Color color) {
		this.setColor(color);
	}
	
	public abstract boolean isValidMove(ChessCell from, ChessCell to);
	
	// boolean canEat(ChessCell cell);
	
	public abstract boolean eat(ChessCell to);

	public Color getColor() {
		return color;
	}

	private void setColor(Color color) {
		this.color = color;
	}
}
