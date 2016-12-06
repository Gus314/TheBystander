package main.graphs.grids;

import java.util.Collection;
import java.util.Map;

import main.exceptions.InvalidGridException;
import main.graphs.DecorationSpecification;
import main.graphs.faces.interfaces.IFace;

public interface IGridFactory 
{
	IGrid Construct(int rows, int columns, Collection<Position> startPositions, Collection<Position> exitPositions, 
					Map<IFace, Position> specialFaces, Collection<DecorationSpecification> decorationSpecifications)  throws InvalidGridException;
}
