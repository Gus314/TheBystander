package bystander.graphs.interfaces;

import java.util.Collection;
import java.util.List;

import bystander.exceptions.InvalidPathException;

public interface IPath
{
    boolean isComplete();
    boolean canAddEdge(IEdge edge);
    void addEdge(IEdge edge) throws InvalidPathException;
    Collection<IEdge> getEdges();
    List<IVertex> getVertices();
    IPath subPath(IVertex start, IVertex end) throws InvalidPathException; // Return the subpath from start to end. May return the empty path.
}
