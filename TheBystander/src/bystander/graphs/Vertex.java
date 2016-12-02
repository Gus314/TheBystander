package bystander.graphs;

import bystander.graphs.interfaces.IVertex;
import bystander.graphs.interfaces.IDecoration;

import java.util.Collection;

import bystander.enums.StartOrExit;

public class Vertex implements IVertex
{
    private Collection<IDecoration> decorations;
    public Collection<IDecoration> getDecorations() {
		return decorations;
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
}