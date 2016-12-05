package bystander.graphs.faces.interfaces;

import java.util.Collection;
import bystander.graphs.interfaces.IVertex;

public interface IFace 
{
	Collection<IVertex> getVertices();
	void addVertex(IVertex vertex);
	IVertex getBottomLeftVertex();
}
