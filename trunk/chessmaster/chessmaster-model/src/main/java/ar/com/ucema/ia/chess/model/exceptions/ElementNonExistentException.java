package ar.com.ucema.ia.chess.model.exceptions;

public class ElementNonExistentException extends BaseChessException {
	private static final long serialVersionUID = 3357192564893544095L;

	public ElementNonExistentException() {
		super();
	}

	public ElementNonExistentException(String description) {
		super(description);
	}
}
