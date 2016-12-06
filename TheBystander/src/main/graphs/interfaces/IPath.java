package main.graphs.interfaces;

import java.util.List;

import main.exceptions.InvalidPathException;

public interface IPath
{
    boolean isComplete();
    boolean canAddEdge(IEdge edge);
    void addEdge(IEdge edge) throws InvalidPathException;
    List<IEdge> getEdges();
    List<IVertex> getVertices();
    IPath subPath(IVertex start, IVertex end) throws InvalidPathException; // Return the subpath from start to end. May return the empty path.
}
