package main.control;

import main.control.interfaces.IGameManager;
import main.enums.StartOrExit;
import main.exceptions.InvalidEdgeException;
import main.exceptions.InvalidPathException;
import main.exceptions.InvalidVertexException;
import main.graphs.Path;
import main.graphs.grids.IGrid;
import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IEdge;
import main.graphs.interfaces.IPath;
import main.graphs.interfaces.IVertex;
import main.graphs.rules.RuleChecker;
import main.graphs.rules.interfaces.IRuleChecker;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Gus on 31/03/2017.
 */
public class GameManager implements IGameManager {
    private IGrid grid;
    private IPath path;

    public GameManager(IGrid grid) {
        this.grid = grid;
        this.path = new Path(true);
    }

    public IGrid getGrid() {
        return grid;
    }

    public IPath getPath() {
        return path;
    }

    public boolean checkSolution() throws InvalidPathException {
        IRuleChecker ruleChecker = new RuleChecker(grid);
        Collection<IArea> areas = grid.determineAreas(path);
        return ruleChecker.isSolution(areas, path);
    }

    public boolean canStart(IVertex v) {
        return (StartOrExit.START == v.getStartOrExit());
    }

    public boolean start(IVertex vertex) {
        if ((path.getVertices().size() == 0)) {
            return path.start(vertex);
        } else {
            return false;
        }
    }

    public boolean removeLastEdge() {
        int pathSize = path.getEdges().size();
        if (pathSize > 0) {
            path.getEdges().remove(pathSize - 1);
            return true;
        } else {
            return false;
        }
    }

    private Collection<IEdge> availableEdges() {
        // Returns a collection of edges that can be added to the current path.
        Collection<IEdge> result = new ArrayList<IEdge>();
        try {
            IVertex lastVertex = path.currentVertex();
            Collection<IEdge> possibleEdges = grid.edgesWithVertex(lastVertex, path);
            for (IEdge edge : possibleEdges) {
                if (edge.getSource() == lastVertex) {
                    if (path.canAddEdge(edge)) {
                        result.add(edge);
                    }
                }
            }
        } catch (InvalidVertexException invex) {
            // A path that has not started yet will have no last vertex.
            // Returning an empty collection seems an appropriate response.
        }

        return result;
    }

    // Returns true if edge successfully traversed, otherwise returns false.
    public boolean traverseEdge(IEdge edge) {
        if (path.canAddEdge(edge)) {
            try {
                path.addEdge(edge);
                return true;
            } catch (InvalidPathException ipex) {
                return false;
            }
        } else {
            return false;
        }
    }

    public IEdge upEdge() throws InvalidEdgeException {
        final String exceptionMessage = "Path has no up edge.";

        try {
            Collection<IEdge> availableEdges = availableEdges();
            for (IEdge edge : availableEdges) {
                if (edge.isUp(path.currentVertex())) {
                    return edge;
                }
            }
        } catch (InvalidVertexException invex) {
            // Note that this should never be hit as a path with available edges must have vertices.
            throw new InvalidEdgeException(exceptionMessage);
        }

        throw new InvalidEdgeException(exceptionMessage);
    }

    public IEdge downEdge() throws InvalidEdgeException {
        final String exceptionMessage = "Path has no down edge.";

        try {
            Collection<IEdge> availableEdges = availableEdges();
            for (IEdge edge : availableEdges) {
                if (edge.isDown(path.currentVertex())) {
                    return edge;
                }
            }
        } catch (InvalidVertexException invex) {
            // Note that this should never be hit as a path with available edges must have vertices.
            throw new InvalidEdgeException(exceptionMessage);
        }

        throw new InvalidEdgeException(exceptionMessage);
    }

    public IEdge leftEdge() throws InvalidEdgeException {
        final String exceptionMessage = "Path has no left edge.";

        try {
            Collection<IEdge> availableEdges = availableEdges();
            for (IEdge edge : availableEdges) {
                if (edge.isLeft(path.currentVertex())) {
                    return edge;
                }
            }
        } catch (InvalidVertexException invex) {
            // Note that this should never be hit as a path with available edges must have vertices.
            throw new InvalidEdgeException(exceptionMessage);
        }

        throw new InvalidEdgeException(exceptionMessage);
    }

    public IEdge rightEdge() throws InvalidEdgeException {
        final String exceptionMessage = "Path has no right edge.";

        try {
            Collection<IEdge> availableEdges = availableEdges();
            for (IEdge edge : availableEdges) {
                if (edge.isLeft(path.currentVertex())) {
                    return edge;
                }
            }
        } catch (InvalidVertexException invex) {
            // Note that this should never be hit as a path with available edges must have vertices.
            throw new InvalidEdgeException(exceptionMessage);
        }

        throw new InvalidEdgeException(exceptionMessage);
    }
}
