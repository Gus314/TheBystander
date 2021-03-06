package main.graphs;

import main.enums.StartOrExit;
import main.graphs.grids.Position;
import main.graphs.interfaces.IVertex;

import java.io.IOException;
import java.io.Serializable;

public class Vertex implements IVertex, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StartOrExit startOrExit;
    private int row;
    private int column;

	public Vertex(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    public Vertex(int row, int column, StartOrExit startOrExit)
    {
        this.row = row;
        this.column = column;
        this.startOrExit = startOrExit;
    }

	public StartOrExit getStartOrExit() {
		return startOrExit;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Position getPosition() {
		return new Position(row, column);
	}

	public String toString()
    {
    	return "(" + row + "," + column + ")";
    }
    
	protected void writeObject(java.io.ObjectOutputStream out) throws IOException
	 {
		 out.writeInt(row);
		 out.writeInt(column);
		 out.writeChars(startOrExit.toString());
	 }
	 
	 protected void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
		 row = in.readInt();
		 column = in.readInt();
		 startOrExit = StartOrExit.valueOf(in.readUTF());
	 }

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (!(other instanceof IVertex)) {
			return false;
		}

		Vertex otherVertex = (Vertex) other;
		return ((otherVertex.getRow() == row) &&
				(otherVertex.getColumn() == getColumn()) &&
				(otherVertex.getStartOrExit() == startOrExit));
	}
}