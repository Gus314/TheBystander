package bystander.graphs;

import bystander.enums.StartOrExit;
import bystander.exceptions.InvalidPathException;
import bystander.graphs.interfaces.ICycle;
import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IPath;

// Conceptually a cycle is a path the may visit the same vertex twice, provided that vertex is both the start and end vertex.
public class Cycle extends Path implements ICycle 
{
	private boolean completed;
	
	public Cycle(IPath path)
	{
		completed = false;
		for(IEdge edge: path.getEdges())
		{
			try 
			{
				addEdge(edge);
			} catch (InvalidPathException e) 
			{
				// Can't happen as the previous path was valid. Should do something anyway just in-case, later.
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Cycle()
	{
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
            /*if(edge.getSource().getStartOrExit() != StartOrExit.START)
            {   // This is true when constructing the master path but not when checking areas.
            // TODO: Tidy this.
                throw new InvalidPathException("Path can only begin at start vertex.");
            }
            else*/
            {
            	getVertices().add(edge.getSource()); // Starting vertex.
            	getVertices().add(edge.getTarget());
            	getEdges().add(edge);
                return;
            }
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
