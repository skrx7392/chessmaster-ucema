package ar.com.ucema.ia.chess.model;

import java.util.ArrayList;
import java.util.List;

import ar.com.ucema.ia.chess.model.exceptions.ElementNonExistentException;

public class ChessColumns {
	public static final String A = "A";
	public static final String B = "B";
	public static final String C = "C";
	public static final String D = "D";
	public static final String E = "E";
	public static final String F = "F";
	public static final String G = "G";
	public static final String H = "H";
	
	private List<PositionAssociation> columnsMapping;
	
	public ChessColumns() {
		this.columnsMapping = new ArrayList<PositionAssociation>();
		put(A, 1);
		put(B, 2);
		put(C, 3);
		put(D, 4);
		put(E, 5);
		put(F, 6);
		put(G, 7);
		put(H, 8);
	}
	
	public String addPositionToColumn(String column, int positions) {
		PositionAssociation asoc = getPositionByName(column);
		int columnNumber = asoc.columnNumber + positions;
		
		if ( columnNumber >= 8 || columnNumber <= 0)
			return column;
			
		return getPositionByNumber(columnNumber).columnName;
	}
	
	public List<String> getColumns() {
		List<String> columns = new ArrayList<String>();
		columns.add(A);
		columns.add(B);
		columns.add(C);
		columns.add(D);
		columns.add(E);
		columns.add(F);
		columns.add(G);
		columns.add(H);
		return columns;
	}
	
	
	private PositionAssociation getPositionByName(String name) {
		for (PositionAssociation pos : columnsMapping) {
			if ( pos.columnName.equals(name))
				return pos;
		}
		throw new ElementNonExistentException("There is not element at the given position");
	}

	private PositionAssociation getPositionByNumber(Integer number) {
		for (PositionAssociation pos : columnsMapping) {
			if ( pos.columnNumber.equals(number))
				return pos;
		}
		throw new ElementNonExistentException("There is not element at the given position");
	}

	private void put(String columnName, Integer columnNumber) {
		this.columnsMapping.add(new PositionAssociation(columnName, columnNumber));
	}
	
	private class PositionAssociation {
		public String columnName;
		public Integer columnNumber;

		public PositionAssociation(String columnName, Integer columnNumnber) {
			super();
			this.columnName = columnName;
			this.columnNumber = columnNumnber;
		}
	}
}
