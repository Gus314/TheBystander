package bystander.graphs.grids;

import java.util.Collection;
import java.util.List;

import bystander.exceptions.InvalidPathException;
import bystander.graphs.faces.interfaces.IFace;
import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.interfaces.IVertex;

public interface IGrid
{
	IVertex[][] getVertices();
	Collection<IEdge> getEdges();
    List<IEdge> edgesWithVertex(IVertex vertex, IPath path);
    void addFace(IFace face);
    void addVertex(IVertex vertex, int row, int column);
    void addEdge(IEdge edge);
    boolean isOnBoundary(IVertex vertex);
    Collection<IArea> determineAreas(IPath path) throws InvalidPathException;
}
