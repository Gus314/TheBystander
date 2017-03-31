package main.graphs.interfaces;

import main.exceptions.InvalidPathException;
import main.exceptions.InvalidVertexException;

import java.util.List;

public interface IPath
{
    boolean start(IVertex vertex);
    boolean isComplete();
    boolean canAddEdge(IEdge edge);
    void addEdge(IEdge edge) throws InvalidPathException;
    List<IEdge> getEdges();
    List<IVertex> getVertices();
    IPath subPath(IVertex start, IVertex end) throws InvalidPathException; // Return the subpath from start to end. May return the empty path.

    IVertex currentVertex() throws InvalidVertexException;
}
