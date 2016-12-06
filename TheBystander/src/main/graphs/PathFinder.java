package main.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import main.enums.StartOrExit;
import main.exceptions.InvalidPathException;
import main.graphs.grids.IGrid;
import main.graphs.interfaces.IEdge;
import main.graphs.interfaces.IPath;
import main.graphs.interfaces.IPathFinder;
import main.graphs.interfaces.IVertex;

public class PathFinder implements IPathFinder
{
	private static final int pathLimit = 1000; // Maximum number of paths, to prevent long execution times.
	private boolean kill;
	
    private void tryPath(IVertex currentVertex, IGrid grid, IPath p, Collection<IPath> results)
    {
    	if(results.size() >= pathLimit)
    	{
    		kill = true;
    	}
    	
    	if(kill)
    	{
    		return;
    	}
    	
        IVertex nextVertex = currentVertex;

        List<IEdge> currentEdges = grid.edgesWithVertex(nextVertex, p);
        if(currentEdges.size() == 0)
        {   // Reached a dead end.
            return;
        }

        if(p.isComplete())
        {
            results.add(p);
            return;
        }

        for(int i = 0; i < currentEdges.size(); ++i)
        {
            IEdge edge = currentEdges.get(i);
            if (p.canAddEdge(edge))
            {
                IPath newPath = new Path(p, true);
                
                try 
                {
					newPath.addEdge(edge);
				} 
                catch (InvalidPathException e) 
                {
					System.out.println(e.getMessage());
				}
                nextVertex = edge.getTarget();
                
            	if(kill)
            	{
            		return;
            	}
                tryPath(nextVertex, grid, newPath, results);
            }
        }

        return;
    }

    public Collection<IPath> findPaths(IGrid grid)
    {
        Collection<IVertex> startVertices = new ArrayList<IVertex>();
        for(IVertex[] vArray: grid.getVertices())
        {
            for(IVertex v : vArray)
            {
                if(v.getStartOrExit() == StartOrExit.START)
                {
                	startVertices.add(v);
                }
            }	
        }

        Collection<IPath> allCompletePaths = new ArrayList<IPath>();
        for(IVertex startVertex: startVertices)
        {
            IPath attempt = new Path(true);
            kill = false;
            Collection<IPath> completePaths = new ArrayList<IPath>();
            tryPath(startVertex, grid, attempt, completePaths);
            allCompletePaths.addAll(completePaths);

        }
        return allCompletePaths;
    }
}
