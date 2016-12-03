package bystander.graphs.interfaces;

import java.util.Collection;

public interface IArea 
{
	Collection<IFace> getFaces();
	void addFace(IFace face);
}
