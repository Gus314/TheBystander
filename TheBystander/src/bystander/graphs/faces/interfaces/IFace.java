package bystander.graphs.faces.interfaces;

import java.util.Collection;

import bystander.exceptions.InvalidVertexException;
import bystander.graphs.interfaces.IVertex;

public interface IFace 
{
	// TODO: Faces can contain 'rules' (e.g. white square, green star).
	
	Collection<IVertex> getVertices();
	void addVertex(IVertex vertex) throws InvalidVertexException;
	IVertex getBottomLeftVertex();
}
