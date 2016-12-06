package main.graphs.grids;

import java.util.Collection;
import java.util.List;

import main.exceptions.InvalidPathException;
import main.graphs.faces.interfaces.IFace;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IEdge;
import main.graphs.interfaces.IPath;
import main.graphs.interfaces.IVertex;

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
