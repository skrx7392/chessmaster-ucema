package ar.com.ucema.ia.chess.model.exceptions;


/**
 * @author Matías Suárez
 */
public class BaseChessException extends RuntimeException {
	private static final long serialVersionUID = -328457658248005817L;
	private String description;
	
	public String getDescription() {
		return description;
	}
	
	public BaseChessException() {
		super();
	}
	
	public BaseChessException(String description) {
		this();
		this.description = description;
	}
	
}
