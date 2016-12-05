package bystander.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bystander.enums.StartOrExit;
import bystander.exceptions.InvalidPathException;
import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IGrid;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.interfaces.IPathFinder;
import bystander.graphs.interfaces.IVertex;

public class PathFinder implements IPathFinder
{
	private boolean kill;
	
    private void tryPath(IVertex currentVertex, IGrid grid, IPath p, Collection<IPath> results)
    {
    	if(results.size() == 1000)//1000000)
    	{
    		// TODO: Temporary restriction.
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
                IPath newPath = new Path(p);
                
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
        IVertex currentVertex = null;
        // TODO: Modify this for multiple start vertices.
        for(IVertex[] vArray: grid.getVertices())
        {
            for(IVertex v : vArray)
            {
                if(v.getStartOrExit() == StartOrExit.START)
                {
                    currentVertex = v;
                }
            }	
        }

        IPath attempt = new Path();
        kill = false;
        Collection<IPath> completePaths = new ArrayList<IPath>();
        tryPath(currentVertex, grid, attempt, completePaths);
        return completePaths;
    }
}
