package ar.com.ucema.ia.chess.model.exceptions;


/**
 * @author Mat�as Su�rez
 */
public class ChessCellNonExistentException extends BaseChessException {
	private static final long serialVersionUID = 1010552306764678824L;

	public ChessCellNonExistentException() {
		super();
	}

	public ChessCellNonExistentException(String description) {
		super(description);
	}
}
