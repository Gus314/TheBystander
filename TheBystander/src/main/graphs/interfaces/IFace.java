package main.graphs.interfaces;

import java.util.Collection;

import main.exceptions.InvalidVertexException;

public interface IFace 
{
	// TODO: Faces can contain 'rules' (e.g. white square, green star).
	
	Collection<IVertex> getVertices();
	void addVertex(IVertex vertex) throws InvalidVertexException;
	IVertex getBottomLeftVertex();
}
