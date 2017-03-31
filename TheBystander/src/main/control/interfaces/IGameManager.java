package main.control.interfaces;

import main.exceptions.InvalidEdgeException;
import main.exceptions.InvalidPathException;
import main.graphs.grids.IGrid;
import main.graphs.interfaces.IEdge;
import main.graphs.interfaces.IPath;
import main.graphs.interfaces.IVertex;

/**
 * Created by Gus on 31/03/2017.
 */
public interface IGameManager {
    IGrid getGrid();

    IPath getPath();

    boolean checkSolution() throws InvalidPathException;

    boolean canStart(IVertex v);

    boolean start(IVertex vertex);

    boolean removeLastEdge();

    boolean traverseEdge(IEdge edge);

    IEdge upEdge() throws InvalidEdgeException;

    IEdge downEdge() throws InvalidEdgeException;

    IEdge leftEdge() throws InvalidEdgeException;

    IEdge rightEdge() throws InvalidEdgeException;
}
