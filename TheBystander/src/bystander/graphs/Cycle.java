package bystander.graphs;

import bystander.enums.StartOrExit;
import bystander.exceptions.InvalidPathException;
import bystander.graphs.interfaces.ICycle;
import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IPath;

/**
 * @author Gus
 * Represents a cycle in a graph, i.e. a path with the same start and end vertex.
 */
public class Cycle extends Path implements ICycle
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean completed;
	
	public Cycle(IPath path)
	{
		super(false); // A cycle cannot be part of the master path.
		completed = false;
		for(IEdge edge: path.getEdges())
		{
			try 
			{
				addEdge(edge);
			}
			catch (InvalidPathException e) 
			{
				// Can't happen as the previous path was valid.
				System.out.println("Unexpected behaviour, copy of valid path was considered invalid.");				
			}
		}
	}
	
	public Cycle()
	{
		super(false); // A cycle cannot be part of the master path.
		completed = false;
	}
	
	public void addEdge(IEdge edge) throws InvalidPathException
    {
		if(completed)
		{
			throw new InvalidPathException("Cannot add edge to completed cycle.");
		}
		
        if(getVertices().contains(edge.getTarget()))
        { 
        	completed = true;
        }

        if(getEdges().size() == 0)
        {
            getVertices().add(edge.getSource()); // Starting vertex.
            getVertices().add(edge.getTarget());
            getEdges().add(edge);
            return;
        }

        if (getEdges().get(getEdges().size()-1).getTarget().getStartOrExit() == StartOrExit.EXIT)
        {
            throw new InvalidPathException("Cannot continue a completed path.");
        }

        if (getEdges().get(getEdges().size()-1).getTarget() == edge.getSource())
        {
        	getVertices().add(edge.getTarget());
        	getEdges().add(edge);
            return;
        }
        else
        {
            throw new InvalidPathException("Cannot add edge to path, source and target do not match.");
        }
    }
	
	
}
