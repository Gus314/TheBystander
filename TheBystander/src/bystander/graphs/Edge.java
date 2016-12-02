package bystander.graphs;

import java.util.Collection;

import bystander.graphs.interfaces.IDecoration;
import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IVertex;

public class Edge implements IEdge
{
	private IVertex source;
	public IVertex getSource() {
		return source;
	}

	public IVertex getTarget() {
		return target;
	}

	public Collection<IDecoration> getDecorations() {
		return decorations;
	}

	private IVertex target;
	private Collection<IDecoration> decorations;

    public Edge(IVertex source, IVertex target)
    {
        this.source = source;
        this.target = target;
    }
}
