package bystander.graphs.grids;

import java.util.Collection;
import java.util.Map;

import bystander.graphs.DecorationSpecification;
import bystander.graphs.faces.interfaces.IFace;

public interface IGridFactory 
{
	IGrid Construct(int rows, int columns, Position start, Position exit, Map<IFace, Position> specialFaces, Collection<DecorationSpecification> decorationSpecifications);
}
