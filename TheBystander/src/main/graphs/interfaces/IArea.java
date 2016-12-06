package main.graphs.interfaces;

import java.util.Collection;

import main.graphs.faces.interfaces.IFace;

public interface IArea 
{
	Collection<IFace> getFaces();
	void addFace(IFace face);
}
