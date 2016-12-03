package bystander.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bystander.enums.StartOrExit;
import bystander.exceptions.InvalidPathException;
import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.interfaces.IVertex;

public class Path implements IPath
{
    private List<IEdge> edges = new ArrayList<IEdge>();
    private List<IVertex> vertices = new ArrayList<IVertex>();
        
    public Collection<IEdge> getEdges() {
		return edges;
	}

	public List<IVertex> getVertices() {
		return vertices;
	}

	public Path()
    {
        ;
    }

    public Path(IPath subPath)
    {
        for(IEdge edge:subPath.getEdges())
        {
            edges.add(edge);
        }

        for(IVertex vertex: subPath.getVertices())
        {
            vertices.add(vertex);
        }

    }

    public boolean isComplete()
    {
        return (edges.size() > 0) && (edges.get(edges.size()-1).getTarget().getStartOrExit() == StartOrExit.EXIT);
    }

    public boolean canAddEdge(IEdge edge)
    {
        if (edges.size() == 0)
        {
            return (edge.getSource().getStartOrExit() == StartOrExit.START);
        }

        if (edges.get(edges.size()-1).getTarget().getStartOrExit() == StartOrExit.EXIT)
        {
            return false;
        }

        if (edges.get(edges.size()-1).getTarget() != edge.getSource())
        {
            return false;
        }

        if(vertices.contains(edge.getTarget()))
        {
            return false;
        }

        return true;
    }

    public void addEdge(IEdge edge) throws InvalidPathException
    {
        if(vertices.contains(edge.getTarget()))
        {
            throw new InvalidPathException("Path cannot visit the same vertex twice.");
        }

        if(edges.size() == 0)
        {
            if(edge.getSource().getStartOrExit() != StartOrExit.START)
            {
                throw new InvalidPathException("Path can only begin at start vertex.");
            }
            else
            {
                vertices.add(edge.getTarget());
                edges.add(edge);
                return;
            }
        }

        if (edges.get(edges.size()-1).getTarget().getStartOrExit() == StartOrExit.EXIT)
        {
            throw new InvalidPathException("Cannot continue a completed path.");
        }

        if (edges.get(edges.size()-1).getTarget() == edge.getSource())
        {
            vertices.add(edge.getTarget());
            edges.add(edge);
            return;
        }
        else
        {
            throw new InvalidPathException("Cannot add edge to path, source and target do not match.");
        }
    }
    
    public IPath subPath(IVertex start, IVertex end) throws InvalidPathException // Return the subpath from start to end. May return the empty path.
    {
    	IPath result = new Path();
    	boolean foundStart = false;
    	
    	for(IEdge e: this.getEdges())
    	{
    		if((!foundStart) && (start == e.getSource()))
    		{
    			foundStart = true;
    			result.addEdge(e);

    		}
    		else if(foundStart)
    		{
    			result.addEdge(e);
    			if(e.getTarget() == end)
    			{
    				// Sub-path complete.
    				break;
    			}
    		}    		
    	}
    	return result;
    }
}
