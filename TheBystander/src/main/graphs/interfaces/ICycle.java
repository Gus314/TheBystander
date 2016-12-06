package main.graphs.interfaces;

import main.exceptions.InvalidPathException;

public interface ICycle extends IPath 
{
	void addEdge(IEdge edge) throws InvalidPathException;
}
