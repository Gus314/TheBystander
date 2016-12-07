package main.graphs;

import main.enums.StartOrExit;
import main.exceptions.InvalidPathException;
import main.graphs.interfaces.IEdge;
import main.graphs.interfaces.IPath;
import main.graphs.interfaces.IVertex;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gus
 * Represents a path along the vertices of a graph, travelling via the edges.
 */
public class Path implements IPath, Serializable
{
	// TODO: Design flaw - edges could be added via getEdges.	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<IEdge> edges = new ArrayList<IEdge>();
    private List<IVertex> vertices = new ArrayList<IVertex>();
    private boolean isMasterPath; // The master path can only begin at a start vertex.
    
	public Path(boolean isMasterPath)
    {
        this.isMasterPath = isMasterPath;
    }

    public Path(IPath subPath, boolean isMasterPath)
    {
    	this.isMasterPath = isMasterPath;

        for(IEdge edge:subPath.getEdges())
        {
            edges.add(edge);
        }

        for(IVertex vertex: subPath.getVertices())
        {
            vertices.add(vertex);
        }

    }

    public List<IEdge> getEdges() {
        return edges;
    }

    public List<IVertex> getVertices() {
        return vertices;
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

        return edges.get(edges.size() - 1).getTarget() == edge.getSource();
    }

    public void addEdge(IEdge edge) throws InvalidPathException
    {
        if(vertices.contains(edge.getTarget()))
        { 
            throw new InvalidPathException("Path cannot visit the same vertex twice.");
        }

        if(edges.size() == 0)
        {
            if(isMasterPath && edge.getSource().getStartOrExit() != StartOrExit.START)
            {   
            	// This is true when constructing the master path but not when checking areas.
                throw new InvalidPathException("Path can only begin at start vertex.");
            }
            else
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
    	IPath result = new Path(false);
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

        if ((getEdges().size() > 0) && (!foundStart)) {
            throw new InvalidPathException("Sub-path was not contained within master path.");
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
