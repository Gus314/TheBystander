package bystander.graphs;

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
	
    private String tryPath(IVertex currentVertex, IGrid grid, IPath p)
    {
    	if(kill)
    	{
    		return "";
    	}
    	
        String result = "";
        IVertex nextVertex = currentVertex;

        List<IEdge> currentEdges = grid.edgesWithVertex(nextVertex, p);
        if(currentEdges.size() == 0)
        {   // Reached a dead end.
            return result;
        }

        if(p.isComplete())
        {
            kill = true;
            result = "";
            for(int i = 0; i < p.getVertices().size(); ++i)
            {
            	result = result + "(" + p.getVertices().get(i).getRow() + ","  +p.getVertices().get(i).getColumn() + ")";
            }
            return result;
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
            		return result;
            	}
                result = result + tryPath(nextVertex, grid, newPath);
            }
        }

        return result;
    }

    public String findPath(IGrid grid)
    {
        IVertex currentVertex = null;
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
        String result = tryPath(currentVertex, grid, attempt);
        System.out.println("Path found: ");
        System.out.println(result);
        return result;
    }
}
