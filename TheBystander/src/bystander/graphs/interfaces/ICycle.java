package bystander.graphs.interfaces;

import bystander.exceptions.InvalidPathException;

public interface ICycle extends IPath 
{
	void addEdge(IEdge edge) throws InvalidPathException;
}
