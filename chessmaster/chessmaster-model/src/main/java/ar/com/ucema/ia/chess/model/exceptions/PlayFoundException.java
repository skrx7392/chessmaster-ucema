package ar.com.ucema.ia.chess.model.exceptions;import ar.com.ucema.ia.chess.model.ChessBoard;public class PlayFoundException extends RuntimeException {	private static final long serialVersionUID = -328457658248005817L;	private String description;	private ChessBoard board;		public String getDescription() {		return description;	}		public PlayFoundException() {		super();	}		public PlayFoundException(String description) {		this();		this.description = description;	}	public PlayFoundException(ChessBoard board) {		this();		this.board = board;	}	public ChessBoard getBoard() {		return board;	}	public void setBoard(ChessBoard board) {		this.board = board;	}	public void setDescription(String description) {		this.description = description;	}		}