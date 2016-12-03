package bystander.graphs.interfaces;

import java.util.Collection;

import bystander.graphs.faces.interfaces.IFace;

public interface IArea 
{
	Collection<IFace> getFaces();
	void addFace(IFace face);
}
