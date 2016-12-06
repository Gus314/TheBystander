package main.graphs.grids;

import java.io.IOException;
import java.io.Serializable;

public class Position implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row;
	private int column;
	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Position(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	@Override
    public int hashCode()
	{
		Integer r = row;
		Integer c = column;
        return (17 * r.hashCode()) + (31 * c.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
       if (!(obj instanceof Position))
            return false;
        if (obj == this)
            return true;

        Position rhs = (Position) obj;
        return (this.row == rhs.row && this.column == rhs.column);
    }
    
	protected void writeObject(java.io.ObjectOutputStream out) throws IOException
	 {
		 out.writeInt(row);
		 out.writeInt(column);
	 }
	 
	 protected void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
		 row = in.readInt();
		 column = in.readInt();
	 }
}
