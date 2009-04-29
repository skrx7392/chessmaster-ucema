package ar.com.ucema.ia.chess.model.exceptions;


/**
 * @author Matías Suárez
 */
public class ChessPieceNonExistentException extends BaseChessException {
	private static final long serialVersionUID = 2681242664738867044L;

	public ChessPieceNonExistentException() {
		super();
	}

	public ChessPieceNonExistentException(String description) {
		super(description);
	}
}
