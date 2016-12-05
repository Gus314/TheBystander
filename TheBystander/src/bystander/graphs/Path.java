package bystander.graphs;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bystander.enums.StartOrExit;
import bystander.exceptions.InvalidPathException;
import bystander.graphs.interfaces.IEdge;
import bystander.graphs.interfaces.IPath;
import bystander.graphs.interfaces.IVertex;

public class Path implements IPath, Serializable
{
	// TODO: Make getEdges() private to prevent getEdges().add producing an impossible path.	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<IEdge> edges = new ArrayList<IEdge>();
    private List<IVertex> vertices = new ArrayList<IVertex>();
        
    public List<IEdge> getEdges() {
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
        if(vertices.contains(edge.getTarget()))
        {
            return false;
        }
        
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
            /*if(edge.getSource().getStartOrExit() != StartOrExit.START)
            {   // This is true when constructing the master path but not when checking areas.
            // TODO: Tidy this.
                throw new InvalidPathException("Path can only begin at start vertex.");
            }
            else*/
            {
            	vertices.add(edge.getSource()); // Starting vertex.
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
    
    public String toString()
    {
    	String result = "<Path>";
    	
    	for(IEdge e: getEdges())
    	{
    		result += e.getSource() + "->";
    	}
    	result += getEdges().get(getEdges().size()-1).getTarget();
    	return result + "</Path>";
    }
    
	protected void writeObject(java.io.ObjectOutputStream out) throws IOException
	 {
		 out.writeInt(vertices.size());
		 IVertex[] verticesArray = (IVertex[]) vertices.toArray();
		 for(int i = 0; i < vertices.size(); i++)
		 {
			 out.writeObject(verticesArray[i]);
		 }
		 out.writeInt(edges.size());	 
		 IEdge[] edgesArray = (IEdge[]) edges.toArray();
		 for(int i = 0; i < edges.size(); i++)
		 {
			 out.writeObject(edgesArray[i]);
		 }
	 }
	 
	 protected void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
		 vertices  = new ArrayList<IVertex>();
		 edges = new ArrayList<IEdge>();
		 
		 int verticesCount = in.readInt();
		 for(int i = 0; i < verticesCount; i++)
		 {
			 vertices.add((IVertex)in.readObject());
		 }
		 int edgesCount = in.readInt(); 
		 for(int i = 0; i < edgesCount; i++)
		 {
			 edges.add((IEdge)(in.readObject()));
		 }
	 }
}
