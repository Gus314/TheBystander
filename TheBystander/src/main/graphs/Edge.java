package main.graphs;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import main.graphs.interfaces.IDecoration;
import main.graphs.interfaces.IEdge;
import main.graphs.interfaces.IVertex;

public class Edge implements IEdge, Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IVertex source;
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

	private IVertex target;
	private Collection<IDecoration> decorations = new ArrayList<IDecoration>();

    public Edge(IVertex source, IVertex target)
    {
        this.source = source;
        this.target = target;
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
