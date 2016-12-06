package main.graphs.interfaces;

import java.util.Collection;

public interface IEdge
{
	IVertex getSource();
	IVertex getTarget();
	Collection<IDecoration> getDecorations();
}
