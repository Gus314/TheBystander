package bystander.graphs.interfaces;

import java.util.Collection;
import java.util.List;

import bystander.exceptions.InvalidPathException;

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
