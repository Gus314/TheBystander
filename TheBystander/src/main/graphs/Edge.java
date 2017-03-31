package main.graphs;

import main.graphs.interfaces.IDecoration;
import main.graphs.interfaces.IEdge;
import main.graphs.interfaces.IVertex;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Edge implements IEdge, Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IVertex source;
	private IVertex target;
	private Collection<IDecoration> decorations = new ArrayList<IDecoration>();

	public Edge(IVertex source, IVertex target) {
		this.source = source;
		this.target = target;
	}

	public IVertex getSource() {
		return source;
	}

	public IVertex getTarget() {
		return target;
	}

	public Collection<IDecoration> getDecorations()
	{
		return decorations;
	}

	public boolean isLeft(IVertex vertex) {
		return source.getColumn() < vertex.getColumn();
	}

	public boolean isRight(IVertex vertex) {
		return source.getColumn() > vertex.getColumn();
	}

	public boolean isUp(IVertex vertex) {
		return source.getRow() < vertex.getRow();
	}

	public boolean isDown(IVertex vertex) {
		return source.getRow() < vertex.getRow();
	}

	public boolean isHorizontallyAligned(IVertex vertex) {
		return !(isLeft(vertex) || isRight(vertex));
	}

	public boolean isVerticallyAligned(IVertex vertex) {
		return !(isUp(vertex) || isDown(vertex));
	}

	protected void writeObject(java.io.ObjectOutputStream out) throws IOException
	 {
		 out.writeObject(source);
		 out.writeObject(target);
		 out.writeInt(decorations.size());
		 IDecoration[] decorationsArray = (IDecoration[]) decorations.toArray();
		 for(int i = 0; i < decorations.size(); i++)
		 {
			 out.writeObject(decorationsArray[i]);
		 }
	 }
	 
	 protected void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
		 source = (IVertex) in.readObject();
		 target = (IVertex) in.readObject();
		 decorations = new ArrayList<IDecoration>();
		 int decorationCount = in.readInt();
		 for(int i = 0; i < decorationCount; i++)
		 {
			 decorations.add((IDecoration)in.readObject());
		 }
	 }
}
