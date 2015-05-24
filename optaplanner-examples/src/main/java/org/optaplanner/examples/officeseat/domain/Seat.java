package org.optaplanner.examples.officeseat.domain;

import org.optaplanner.examples.common.domain.AbstractPersistable;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("Seat")
public class Seat extends AbstractPersistable {

	private int col;
	private int row;

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public String toString() {
		return "[Seat] id = " + id + ", col = " + col + ", row = " + row;
	}

	// ---------------
	// Complex methods
	// ---------------
	
	public long getDistance(Seat other) {
		int horizontal = Math.abs(col - other.col);
		int vertical = Math.abs(row - other.row);
		return (horizontal * 2) * vertical; // column distance is worse.
	}
}
