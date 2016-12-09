package main.graphs.interfaces;

import main.graphs.faces.interfaces.IFace;
import main.graphs.grids.IGrid;

import java.util.Collection;

public interface IArea
{
	Collection<IFace> getFaces();
	void addFace(IFace face);

    void returnSpecialFaces(IGrid grid);
}
